package com.xo.web.mgr;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.dao.UserPermissionDAO;
import com.xo.web.models.dao.UserPermissionDAOImpl;
import com.xo.web.models.dao.UsersPermissionsResourceInstanceDAO;
import com.xo.web.models.dao.UsersPermissionsResourceInstanceDAOImpl;
import com.xo.web.models.system.Permission;
import com.xo.web.models.system.ResourceType;
import com.xo.web.models.system.User;
import com.xo.web.models.system.UserPermission;
import com.xo.web.models.system.UserPermissionResourceInstance;
import com.xo.web.models.system.UserPermissionResourceInstanceId;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.RowLevelPermissionDto;

public class UserPermissionResourceInstanceLogic extends RowLevelPermissionLogic<UserPermissionResourceInstance, UserPermissionResourceInstanceId> {

	private final UsersPermissionsResourceInstanceDAO usersPermissionsResourceInstanceDAO;
	private final UserDAO userDAO;
	private final UserPermissionDAO userPermissionDAO;
	
	public UserPermissionResourceInstanceLogic() {
		super(new UsersPermissionsResourceInstanceDAOImpl());
		this.usersPermissionsResourceInstanceDAO = (UsersPermissionsResourceInstanceDAO) this.entityDao;
		this.userDAO = new UserDAOImpl();
		this.userPermissionDAO = new UserPermissionDAOImpl();
	}

	public void save(RowLevelPermissionDto rowLevelPermissionDto) throws XODAOException {
		if(rowLevelPermissionDto != null) {

			Collection<UserPermission> userPermissions = this.userPermissionDAO.findAllByRlpIDAndResourceTypeId(rowLevelPermissionDto.userId, rowLevelPermissionDto.resourceTypeId);
			if(XoUtil.hasData(userPermissions)) {
				for(UserPermission userPermission : userPermissions) {
					createUserRLP(rowLevelPermissionDto, userPermission);
				}
			} else {
				ResourceType resourceType = this.resourceTypeDao.find(rowLevelPermissionDto.resourceTypeId);
				User user = this.userDAO.find(rowLevelPermissionDto.userId);
				Collection<Permission> resourcePermissions = resourceType.getPermissions();
				if(XoUtil.hasData(resourcePermissions)) {
					for(Permission permission : resourcePermissions) {
						UserPermission userPermission = this.userPermissionDAO.save(new UserPermission(permission, user));
						createUserRLP(rowLevelPermissionDto, userPermission);
					}
				}
			}
		}
	}

	private void createUserRLP(RowLevelPermissionDto rowLevelPermissionDto,
			UserPermission userPermission) {
		UserPermissionResourceInstance userPermissionResourceInstance;
		userPermissionResourceInstance = new UserPermissionResourceInstance(userPermission, rowLevelPermissionDto.entityId,	rowLevelPermissionDto.displayText);
		userPermissionResourceInstance.setCreatedDate(new Date());
		userPermissionResourceInstance.setActive(true);
		this.usersPermissionsResourceInstanceDAO.save(userPermissionResourceInstance);
	}

	protected Set<RowLevelPermissionDto> convertEntitiesToDtos(Collection<UserPermissionResourceInstance> userPermissionResourceInstances) {
		Set<RowLevelPermissionDto> rowLevelPermissionDtos = new HashSet<RowLevelPermissionDto>();
		if(XoUtil.hasData(userPermissionResourceInstances)) {
			for(UserPermissionResourceInstance userPermissionResourceInstance : userPermissionResourceInstances) {
				rowLevelPermissionDtos.add(new RowLevelPermissionDto(userPermissionResourceInstance));
			}
		}
		return rowLevelPermissionDtos;
	}
}
