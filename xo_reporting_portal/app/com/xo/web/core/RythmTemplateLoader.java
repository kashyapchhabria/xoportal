package com.xo.web.core;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.rythmengine.RythmEngine;

import com.xo.web.util.XoAppConfigKeys;

import play.Configuration;
import play.Logger;

//@SuppressWarnings("unused")
public class RythmTemplateLoader {

    private final Configuration config;
    private final RythmEngine rythmEngine;
    private final String templateDir;
    private String prodTemplateDir;
    private final boolean appMode;
    private URI templateFolderUri;
    
    private static RythmTemplateLoader rythmTemplateLoader;

    /**
     * Inject a bytecode helper implementation into Rythm in memory compilation system to allow user application specified way to locate class bytecode.
     * Default value: null
     */
	private static final String BYCODE_HELPER_IMPL = "rythm.engine.class_loader.byte_code_helper.impl";

    /**
     * Set the parent class loader to the Rythm template class loader(org.rythmengine.internal.compiler.TemplateClassLoader). 
     * In certain environment, for example, Play!framework, a specific class loader is used to manager application classes. 
     * Thus it needs to set the parent of the tempmlate class loader so that when a application class is encountered in the template source, the correct class can be located.
     * Default value: Thread.currentThread().getContextClassLoader()
     */
    private static final String CLASS_LOADER_PARENT_IMPL = "rythm.engine.class_loader.parent.impl";
    
    /**
     * Enable engine to write to disk to output the generated source file and cache bytecode files. By default file write is enabled. 
     * However in certain environment, for example, Google Applicaiton Engine, file writing is forbidden. 
     * In which case you want to disable file write of the Rythm engine
     * Default value: true
     */
    private static final String FILE_WRITE_ENABLED = "rythm.engine.file_write.enabled";
    
    /**
     * Set the ID to the engine instance. The ID been set could be later on retreived via RythmEngine.id() instance method call. 
     * This is useful when a user application instantiated multiple rythm engines, and need to distinct one from the others.
     */
    private static final String ENGINE_ID = "rythm.engine.id";

    /**
     * Use this configuration to instruct the engine to load precompiled template bytecode directly instead of parsing and compiling the template source. 
     * Usually used in product mode when bytecode has already been precompiled.
     * Default value: false
     */
    private static final String LOAD_PRECOMPILED_ENABLED = "rythm.engine.load_precompiled.enabled";

    /**
     * Set the engine running mode. The Rythm engine could be running in either dev or prod mode.
     * When engine is running in dev mode, each time any form of render method is called, the engine will check if the template source file has changed since last parsing/compilation or not. 
     * If the file has been changed, then it will reload the template source file and go through the parsing/compilation process to generate new bytecodes. 
     * Otherwise, the engine will just continue to execute the existing bytecodes.
     * Another thing about dev mode is, when the engine.file_write is enabled, 
     * then the generated bytecodes will be persistent to home.tmp directory so that they can be quickly loaded when next time the program started.
     * Default value: prod
     */
    private static final String ENGINE_MODE = "rythm.engine.mode";

    /**
     * When engine is run in precompile mode the generated template bytecodes will be cached to precompiled directory, otherwise they will be cached to the tmp directory. 
     * The cached bytecode files can be loaded by engine when engine.load_precompiled set to true
     * Default value: false
     */
    private static final String PRECOMPILE_MODE_ENABLED = "rythm.engine.precompile_mode.enabled";

    /**
     * Set the root directory of template source files. 
     * This configuration is used by Rythm to locate template source files if you have not configured the resource loader implementation or your configured resource loader failed to load the template specified.
     * Default value: The first rythm folder found in the class root of current Java process:
     */
    private static final String TEMPLATE_DIR = "rythm.home.template.dir";

    /**
     * Set the root directory to output the generated template java code and byte code files. 
     * This is very helpful when running Rythm engine in dev mode, as developer can set the tmp dir as a source root folder in his/her IDE, 
     * and set breakpoint to the generated java code files to debug the template. 
     * The byte code been cached is also useful as Rythm engine will try to load the cached bytecodes directly if the template source timestamp is not changed, 
     * and thus safe the template parsing and compilation time when running in dev mode.
     * Default value: The __rythm folder under the system tmp directory:
     */
    private static final String TEMPLATE_TMP_DIR = "rythm.home.tmp.dir";

    /**
     * Set the root directory to output the generated template byte code files when the engine is running under precompile mode. 
     * The generated template byte codes can be loaded in later process when the load_precompiled option is turned on
     * Default value: null
     */
    private static final String PRECOMPILED_DIR = "rythm.home.precompiled.dir";

    /**
     * Configure resource loader implementation. When resource loader is configured, 
     * then the engine will ask it to load template source when needed.
     * Default value: null
     */
    private static final String LOADER_IMPLS = "resource.loader.impls";

    /**
     * Set suffix (e.g. “.rythm“) of the template source files. Note this setting is favor by Rythm's built-in file resource loader. 
     * However when the resource loader is configured, it is up to the user implementation to decide whether favor this setting or not.
     * Default value: empty string ""
     */
    private static final String NAME_SUFFIX = "rythm.resource.name.suffix";

    /**
     * Set the cache service implementation. This configuration is only effective when cache is enabled.
     * Default value: Rythm's built-in simple cache service
     */
    private static final String CACHE_ENABLED = "rythm.cache.enabled";

    /**
     * When this configuration is set to true, then cache will only be effective when Rythm is running in prod mode. 
     * Disable cache at dev mode makes it convenient for development and debugging.
     * Default value: true
     */
    private static final String CACHE_PROD_ONLY_ENABLED = "rythm.cache.prod_only.enabled";

    /**
     * Enable/disable logging in Rythm.
     * Default value: true
     */
    private static final String LOG_ENABLED = "rythm.log.enabled";

    /**
     * Set the locale which impact the output of @i18n built-in function, varieties of format built-in transformers etc.
     * The locale set with this configuration can be overwritten by @locale directive when executing the template.
     * Default value: java.util.Locale.getDefault()
     */
    private static final String I18N_LOCALE = "rythm.i18n.locale";

    /**
     * Set message sources, should be a String separated by “,“, for example, "format,exception,window". 
     * Note the setting is used by Rythm's default i18n.message.resolver implementation. 
     * If user application configure the customized message resolver, 
     * it is up to that message resolver implementation to decide whether use this configuration or not.
     * Default value: "messages"
     */
    private static final String I18N_MESSAGE_SOURCES = "rythm.i18n.message.sources";

    /**
     * Configure i18n message resolver which support i18n message lookup.
     * Default value: org.rythmengine.extension.II18nMessageResolver.DefaultImpl
     */
    private static final String I18N_MESSAGE_RESOLVER_IMPL = "rythm.i18n.message.resolver.impl";

    private static final String TRANSFORMER_UDT = "rythm.transformer.udt";

    
    /**
     * Purposefully removed out properties
     * <ul>
     * <li>rythm.engine.plugin.version</li>
     * <li>rythm.feature.natural_template.enabled</li>
     * <li>rythm.feature.smart_escape.enabled</li>
     * <li>rythm.feature.transform.enabled</li>
     * <li>rythm.feature.type_inference.enabled</li>
     * <li>feature.dynamic_exp.enabled</li>
     * <li>rythm.codegen.byte_code_enhancer.impl</li>
     * <li>rythm.codegen.compact.enabled</li>
     * <li>rythm.codegen.source_code_enhancer.impl</li>
     * <li>rythm.render.listner.impl</li>
     * <li>rythm.render.exception_handler.impl</li>
     * <li>rythm.built_in.code_type.enabled</li>
     * <li>rythm.built_in.transformer.enabled</li>
     * <li>rythm.default.code_type.impl</li>
     * <li>rythm.default.cache_ttl</li>
     * <li>rythm.cache.service.impl</li>
     * <li>rythm.cache.duration_parser.impl</li>
     * <li>rythm.source.java.enabled<li>
     * <li>rythm.log.factory.impl</li>
     * <li>rythm.source.template.enabled</li>
     * <li>rythm.sandbox.security_manager.impl</li>
     * <li>rythm.sandbox.timeout</li>
     * <li>rythm.sandbox.restricted_class</li>
     * <li>rythm.sandbox.allowed_system_properties</li>
     * <li>rythm.sandbox.thread_factory.impl</li>
     * <li>rythm.sandbox.pool.size</li>
     * 
     * </ul>
     */
    private RythmTemplateLoader(Configuration config) {
        this.config = config;
        this.templateDir = this.config.getString(XoAppConfigKeys.TEMPLATE_DIR);
        this.appMode = play.api.Play.isProd(play.api.Play.current());
        try {
            
            if(this.appMode) {
            	prodTemplateDir = "conf/" + this.templateDir;
            	File templateFileLocation = new File(prodTemplateDir);
            	this.templateFolderUri = templateFileLocation.toURI();
            } else {
            	prodTemplateDir = this.templateDir;
            	this.templateFolderUri = play.api.Play.current().classloader().getResource(prodTemplateDir).toURI();
            }
            Logger.info("Template Path : " + this.templateFolderUri.getRawPath());
            Map<String, String> env = new HashMap<>();
            if(this.templateFolderUri.getRawPath().startsWith("jar")) {
            	env.put("create", "true");
            	FileSystem zipfs = FileSystems.newFileSystem(this.templateFolderUri, env);
            }
        } catch (URISyntaxException e) {
        	Logger.error("Error while initiating the tempalte loader.", e);
        } catch (IOException e) {
        	Logger.error("Error while initiating the tempalte loader.", e);
        }
        
        Map<String, Object> rythmConfigs = new HashMap<>();
        rythmConfigs.put(ENGINE_MODE, this.config.getString(ENGINE_MODE));
        rythmConfigs.put(TEMPLATE_DIR, this.templateFolderUri.getPath());
        rythmEngine = new RythmEngine(rythmConfigs);
    }

    public static final RythmTemplateLoader create(Configuration config) {
        if(rythmTemplateLoader == null) {
            return new RythmTemplateLoader(config);
        } else {
            return RythmTemplateLoader.rythmTemplateLoader;
        }
    }

    public static final RythmTemplateLoader getTemplateLoader() {
    	if(rythmTemplateLoader == null) {
            return new RythmTemplateLoader(play.Play.application().configuration());
        } else {
            return RythmTemplateLoader.rythmTemplateLoader;
        }
    }
 
    public RythmEngine getTemplateEngine() {
        return this.rythmEngine;
    }

    public String render(String templateName, Object... templateArgs) {
        try {
        	URI uri = null;
        	String templatePath = this.prodTemplateDir + templateName;
        	if(this.appMode) {
        		uri = new File(templatePath).toURI();
        	} else {
        		uri = play.api.Play.current().classloader().getResource(templatePath).toURI();	
        	}
            System.out.println("Template Path : " + uri);
            //String content = new String(Files.readAllBytes(Paths.get(uri)));
            return this.rythmEngine.render(templateName, templateArgs);
        } catch (URISyntaxException e) {
        	Logger.error("Error while loading the tempalte : " + templateName, e);
        } /*catch (IOException e) {
        	Logger.error("Error while loading the tempalte : " + templateName, e);
        }*/
        return "Error";
    }
}