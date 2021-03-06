package com.xo.web.models.system;
// Generated 30 May, 2015 2:15:28 PM by Hibernate Tools 4.3.1



/**
 * ClientJobsConfigurationsId generated by hbm2java
 */
@SuppressWarnings("serial")
public class ClientJobsConfigurationsId  implements java.io.Serializable {

     private int clientId;
     private int jobId;
     private int configInstanceId;

    public ClientJobsConfigurationsId() {
    }

    public ClientJobsConfigurationsId(int clientId, int jobId, int configInstanceId) {
       this.clientId = clientId;
       this.jobId = jobId;
       this.configInstanceId = configInstanceId;
    }
   
    public int getClientId() {
        return this.clientId;
    }
    
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
    public int getJobId() {
        return this.jobId;
    }
    
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
    public int getConfigInstanceId() {
        return this.configInstanceId;
    }
    
    public void setConfigInstanceId(int configInstanceId) {
        this.configInstanceId = configInstanceId;
    }

   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ClientJobsConfigurationsId) ) return false;
		 ClientJobsConfigurationsId castOther = ( ClientJobsConfigurationsId ) other; 
         
		 return (this.getClientId()==castOther.getClientId())
 && (this.getJobId()==castOther.getJobId())
 && (this.getConfigInstanceId()==castOther.getConfigInstanceId());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getClientId();
         result = 37 * result + this.getJobId();
         result = 37 * result + this.getConfigInstanceId();
         return result;
   }   


}


