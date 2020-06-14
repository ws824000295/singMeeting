/**
 * ConfWebServiceInterfacePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.boot.common.util.meet.v13;

public interface ConfWebServiceInterfacePortType extends java.rmi.Remote {

    /**
     * Service definition of function ns__CreateConf
     */
    public long createConf(java.lang.String sSubject, java.lang.String sCreator) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddConfParticipant
     */
    public int addConfParticipant(long confid, java.lang.String xmlParticipant) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelConfParticipants
     */
    public int delConfParticipants(long confid, java.lang.String useridset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DestroyConf
     */
    public int destroyConf(long nGroupID) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryConf
     */
    public java.lang.String queryConf(long confid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryConfParticipants
     */
    public java.lang.String queryConfParticipants(long confid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateConfInfo
     */
    public int updateConfInfo(long confid, java.lang.String xmlinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserConfAccessible
     */
    public java.lang.String queryUserConfAccessible(long userid, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserConfAccessibleTotalCount
     */
    public int queryUserConfAccessibleTotalCount(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessibleConfByTimeInterval
     */
    public java.lang.String queryUserAccessibleConfByTimeInterval(long userid, int usercondition, long begintime, long endtime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessbileConfByTimeIntervalTotalCount
     */
    public int queryUserAccessbileConfByTimeIntervalTotalCount(long userid, int usercondition, long begintime, long endtime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessibleConfByStartTime
     */
    public java.lang.String queryUserAccessibleConfByStartTime(long userid, int usercondition, long minbegintime, long maxbegintime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessbileConfByStartTimeTotalCount
     */
    public int queryUserAccessbileConfByStartTimeTotalCount(long userid, int usercondition, long minbegintime, long maxbegintime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessibleConfByTime
     */
    public java.lang.String queryUserAccessibleConfByTime(long userid, int usercondition, long minbegintime, long maxbegintime, long minendtime, long maxendtime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessbileConfByTimeTotalCount
     */
    public int queryUserAccessbileConfByTimeTotalCount(long userid, int usercondition, long minbegintime, long maxbegintime, long minendtime, long maxendtime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgConfByStartTime
     */
    public java.lang.String queryOrgConfByStartTime(long orgid, long minbegintime, long maxbegintime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgConfByStartTimeTotalCount
     */
    public int queryOrgConfByStartTimeTotalCount(long orgid, long minbegintime, long maxbegintime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddOrganization
     */
    public long addOrganization(long adminid, java.lang.String orgname, java.lang.String industry, java.lang.String address, java.lang.String tel, java.lang.String mobile, java.lang.String remark, java.lang.String defaultpwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddDepartment
     */
    public long addDepartment(long orgid, long parentDepartmentID, java.lang.String name, java.lang.String remark) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddDeparmtmentUser
     */
    public int addDeparmtmentUser(long userid, long deptid, long orgid, int role) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddDepartmentBatchUser
     */
    public int addDepartmentBatchUser(long deptid, long orgid, java.lang.String userset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgDepts
     */
    public java.lang.String queryOrgDepts(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessibleDepts
     */
    public java.lang.String queryUserAccessibleDepts(long userid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptsOfDeptManager
     */
    public java.lang.String queryDeptsOfDeptManager(long managerid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeptSortOperationBatch
     */
    public int deptSortOperationBatch(java.lang.String xml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeptMembersSortOperationBatch
     */
    public int deptMembersSortOperationBatch(java.lang.String xml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptInfo
     */
    public java.lang.String queryDeptInfo(long deptid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptManagerLogin
     */
    public java.lang.String queryDeptManagerLogin(java.lang.String account, java.lang.String pwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptManagersInfo
     */
    public java.lang.String queryDeptManagersInfo(long deptid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddDeptManager
     */
    public boolean addDeptManager(long deptid, long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelDeptManager
     */
    public boolean delDeptManager(long deptid, long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelDeptManagerByDeptId
     */
    public int delDeptManagerByDeptId(long deptid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelAllDeptManagerByUserId
     */
    public boolean delAllDeptManagerByUserId(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IsDeptManager
     */
    public boolean isDeptManager(java.lang.String account) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAncestorManagerOfUser
     */
    public java.lang.String queryAncestorManagerOfUser(long orgid, long deptid, long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryChildManagerOfUser
     */
    public java.lang.String queryChildManagerOfUser(long orgid, long deptid, long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IsChildDept
     */
    public boolean isChildDept(long orgid, long pDeptid, long cDeptid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IsDeptManagerById
     */
    public boolean isDeptManagerById(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptManagerInfo
     */
    public java.lang.String queryDeptManagerInfo(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddSipServer
     */
    public long addSipServer(java.lang.String strHost, long nPort, java.lang.String strDomain) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QuerySipServer
     */
    public java.lang.String querySipServer() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelSipServer
     */
    public boolean delSipServer(long id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateSipServer
     */
    public boolean updateSipServer(long id, java.lang.String xml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IsSipExtNumOccupied
     */
    public boolean isSipExtNumOccupied(long orgid, long sipextnum) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QuerySubDeptTotalCount
     */
    public int querySubDeptTotalCount(long parentdeptid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QuerySubDepts
     */
    public java.lang.String querySubDepts(long parentdeptid, long orgid, int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAllSubDepts
     */
    public java.lang.String queryAllSubDepts(long orgid, long deptid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptMember
     */
    public java.lang.String queryDeptMember(long deptid, long orgid, int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptMemberTotalCount
     */
    public int queryDeptMemberTotalCount(long deptid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelOrganization
     */
    public int delOrganization(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelDepartment
     */
    public int delDepartment(long deptid, int delmode) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelDepartmentUser
     */
    public int delDepartmentUser(long userid, long deptid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelDepartmentBatchUser
     */
    public int delDepartmentBatchUser(long deptid, long orgid, java.lang.String userset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrganization
     */
    public void queryOrganization(long orgid, javax.xml.rpc.holders.StringHolder orgname, javax.xml.rpc.holders.StringHolder industry, javax.xml.rpc.holders.StringHolder address, javax.xml.rpc.holders.StringHolder tel, javax.xml.rpc.holders.StringHolder mobile, javax.xml.rpc.holders.StringHolder logo, javax.xml.rpc.holders.StringHolder remark, javax.xml.rpc.holders.StringHolder defaultpwd, javax.xml.rpc.holders.LongHolder adminid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgManager
     */
    public java.lang.String queryOrgManager(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgnameExisted
     */
    public boolean queryOrgnameExisted(java.lang.String orgname) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdataOrganization
     */
    public int updataOrganization(long orgid, java.lang.String xmlinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateDeptInfo
     */
    public int updateDeptInfo(long deptid, java.lang.String xmldeptinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQuerySubDeptsInfo
     */
    public java.lang.String fuzzyQuerySubDeptsInfo(long orgid, long parentdeptid, java.lang.String value, int type, int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQuerySubDeptsTotalCount
     */
    public int fuzzyQuerySubDeptsTotalCount(long parentdeptid, long orgid, java.lang.String value, int type) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryDeptMember
     */
    public java.lang.String fuzzyQueryDeptMember(long deptid, long orgid, java.lang.String value, int type, int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryDeptMemberTotalCount
     */
    public int fuzzyQueryDeptMemberTotalCount(long deptid, java.lang.String value, int type, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserDepts
     */
    public java.lang.String queryUserDepts(long userid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccessibleOrganization
     */
    public java.lang.String queryUserAccessibleOrganization(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryInvisibleDept
     */
    public java.lang.String queryInvisibleDept(long deptid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddInvisibleDepts
     */
    public int addInvisibleDepts(long deptid, java.lang.String invisibledeptset, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddBlockedDepts
     */
    public int addBlockedDepts(long deptid, java.lang.String blockdeptset, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelInvisibleDepts
     */
    public int delInvisibleDepts(long deptid, java.lang.String invisibledeptset, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptMemberInDepts
     */
    public java.lang.String queryDeptMemberInDepts(long orgid, java.lang.String deptset, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptMemberInDeptsTotalCount
     */
    public int queryDeptMemberInDeptsTotalCount(long orgid, java.lang.String deptset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgAllMember
     */
    public java.lang.String queryOrgAllMember(long orgid, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgAllMemberTotalCount
     */
    public int queryOrgAllMemberTotalCount(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryBlockedDeparts
     */
    public java.lang.String queryBlockedDeparts(long deptid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelBatchDepartment
     */
    public int delBatchDepartment(java.lang.String deptset, int delmode) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDeptNameExisted
     */
    public boolean queryDeptNameExisted(long parentid, long orgid, java.lang.String name) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgs
     */
    public java.lang.String queryOrgs(int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgsCount
     */
    public int queryOrgsCount() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryOrgsByName
     */
    public java.lang.String fuzzyQueryOrgsByName(java.lang.String orgname, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryOrgsByNameCount
     */
    public int fuzzyQueryOrgsByNameCount(java.lang.String orgname) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddBatchUser
     */
    public java.lang.String addBatchUser(java.lang.String xmluser) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddUser
     */
    public long addUser(java.lang.String xmluser) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelUser
     */
    public int delUser(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelUsers
     */
    public int delUsers(java.lang.String userset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAccountExisted
     */
    public long queryUserAccountExisted(java.lang.String email) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryBatchUserAccountExisted
     */
    public java.lang.String queryBatchUserAccountExisted(java.lang.String xmlemails) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserConciseInfo
     */
    public java.lang.String queryUserConciseInfo(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserDetailInfo
     */
    public java.lang.String queryUserDetailInfo(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateUserPwd
     */
    public int updateUserPwd(long userid, java.lang.String pwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateUserInfo
     */
    public int updateUserInfo(long userid, java.lang.String xmlinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUsers
     */
    public java.lang.String queryUsers(int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUsersTotalCount
     */
    public int queryUsersTotalCount() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryUserByCondition
     */
    public java.lang.String fuzzyQueryUserByCondition(int condition, java.lang.String value, int starindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__FuzzyQueryUserCountByCondition
     */
    public int fuzzyQueryUserCountByCondition(int condition, java.lang.String value) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgUserExisted
     */
    public boolean queryOrgUserExisted(long userid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddSMSAccount
     */
    public long addSMSAccount(java.lang.String host, java.lang.String corpid, java.lang.String loginname, java.lang.String pwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddOrgSMSAccount
     */
    public int addOrgSMSAccount(long smsid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddUserSMSAddNO
     */
    public java.lang.String addUserSMSAddNO(java.lang.String users, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgSMSAccount
     */
    public java.lang.String queryOrgSMSAccount(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserSMSInfo
     */
    public java.lang.String queryUserSMSInfo(long userid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QuerySMSAccount
     */
    public java.lang.String querySMSAccount(long smsid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateSMSAccount
     */
    public int updateSMSAccount(long smsid, java.lang.String host, java.lang.String corpid, java.lang.String loginname, java.lang.String pwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelSMSAccount
     */
    public int delSMSAccount(long smsid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelOrgSMSAccount
     */
    public int delOrgSMSAccount(long orgid, long smsid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelUserSMS
     */
    public int delUserSMS(long userid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAllSMSAccount
     */
    public java.lang.String queryAllSMSAccount(int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAllSMSAccountCount
     */
    public int queryAllSMSAccountCount() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserCallRecordByTime
     */
    public java.lang.String queryUserCallRecordByTime(long userid, long beginTime, long endTime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserCallRecordCountByTime
     */
    public int queryUserCallRecordCountByTime(long userid, long beginTime, long endTime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserSMSRecordByTime
     */
    public java.lang.String queryUserSMSRecordByTime(long userid, long beginTime, long endTime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserSMSRecodCountByTime
     */
    public int queryUserSMSRecodCountByTime(long userid, long beginTime, long endTime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgCallRecordsByTime
     */
    public java.lang.String queryOrgCallRecordsByTime(long orgid, long beginTime, long endTime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgCallRecordsCountByTime
     */
    public int queryOrgCallRecordsCountByTime(long orgid, long beginTime, long endTime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgSMSRecordsByTime
     */
    public java.lang.String queryOrgSMSRecordsByTime(long orgid, long beginTime, long endTime, int startindex, int limitcount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgSMSRecordsCountByTime
     */
    public int queryOrgSMSRecordsCountByTime(long orgid, long beginTime, long endTime) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAdministrableAnnouncement
     */
    public java.lang.String queryUserAdministrableAnnouncement(int logintype, long orgid, long creatorid, int startindex, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserAdministrableAnnouncementTotalCount
     */
    public int queryUserAdministrableAnnouncementTotalCount(int logintype, long orgid, long creatorid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAnnouncementInfo
     */
    public java.lang.String queryAnnouncementInfo(int logintype, long annid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAnnounceOrgDepts
     */
    public java.lang.String queryAnnounceOrgDepts(int annid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddAnnouncement
     */
    public long addAnnouncement(int logintype, java.lang.String xml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddAnnounceDepts
     */
    public int addAnnounceDepts(java.lang.String depts, long orgid, long annid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateAnnouncement
     */
    public int updateAnnouncement(int logintype, long annid, java.lang.String xmlinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelAnnounceDepts
     */
    public int delAnnounceDepts(java.lang.String deptset, long annid, long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelAnnoucement
     */
    public int delAnnoucement(int logintype, java.lang.String annidset) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IssueAnnouncement
     */
    public int issueAnnouncement(java.lang.String annidset, int logintype) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddFileBoxs
     */
    public int addFileBoxs(java.lang.String xmlBoxsInfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgFileBox
     */
    public java.lang.String queryOrgFileBox(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryFileBox
     */
    public java.lang.String queryFileBox(long id, int boxtype) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelFileBox
     */
    public int delFileBox(long boxid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddAdminFileBoxResource
     */
    public int addAdminFileBoxResource(java.lang.String xmlresource) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminFileBoxResource
     */
    public java.lang.String queryAdminFileBoxResource(long adminid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddAdmin
     */
    public long addAdmin(java.lang.String xmlinfo) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AdminLogin
     */
    public java.lang.String adminLogin(java.lang.String username, java.lang.String pwd) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminInfo
     */
    public java.lang.String queryAdminInfo(long id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminByName
     */
    public java.lang.String queryAdminByName(java.lang.String username) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminAccountExisted
     */
    public int queryAdminAccountExisted(java.lang.String username) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateAdminInfo
     */
    public int updateAdminInfo(long adminid, java.lang.String xml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminOrgInfo
     */
    public java.lang.String queryAdminOrgInfo(long id) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryAdminSubordinate
     */
    public java.lang.String queryAdminSubordinate(long adminid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddUserPermission
     */
    public int addUserPermission(java.lang.String userspermission) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryUserPermission
     */
    public java.lang.String queryUserPermission(long userid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateUserPermission
     */
    public int updateUserPermission(java.lang.String userspermissionxml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UserLogin
     */
    public java.lang.String userLogin(java.lang.String username, java.lang.String pass) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddOrgPermission
     */
    public int addOrgPermission(java.lang.String orgpermission) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgPermission
     */
    public java.lang.String queryOrgPermission(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateOrgPermission
     */
    public int updateOrgPermission(java.lang.String orgpermissionxml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryConsumeResource
     */
    public java.lang.String queryConsumeResource() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ConnectMasterServer
     */
    public java.lang.String connectMasterServer(int domainId, java.lang.String sServerIp, int port, java.lang.String password) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DisConnectMasterServer
     */
    public boolean disConnectMasterServer() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryMasterConnection
     */
    public int queryMasterConnection() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ChangeDomainId
     */
    public boolean changeDomainId() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDomainName
     */
    public java.lang.String queryDomainName() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__NotifyCloseServer
     */
    public boolean notifyCloseServer() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__NotifyStartServer
     */
    public boolean notifyStartServer() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__NotifyAuthComplete
     */
    public java.lang.String notifyAuthComplete(java.lang.String sAuthInfoXml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AdjustPermission
     */
    public java.lang.String adjustPermission() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryDongle
     */
    public java.lang.String queryDongle() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryExistVideoConfCount
     */
    public int queryExistVideoConfCount(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryConfVideos
     */
    public java.lang.String queryConfVideos(long orgid, java.lang.String confSet, int startIndex, int limitCount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOnlinePeoplesCount
     */
    public java.lang.String queryOnlinePeoplesCount(java.lang.String sDateList) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SetSetupUpIp
     */
    public int setSetupUpIp(java.lang.String sIP, java.lang.String sSetupUpName) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryOrgOnlineNumberOfPeople
     */
    public java.lang.String queryOrgOnlineNumberOfPeople() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__InviteJoinConf
     */
    public int inviteJoinConf(long nConfID, java.lang.String sAccount, java.lang.String sSigned) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__IsInConf
     */
    public int isInConf(java.lang.String sAccount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__KickConf
     */
    public int kickConf(long nConfID, java.lang.String sAccount) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__CloseConf
     */
    public int closeConf(long nConfID) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SetConfBanner
     */
    public int setConfBanner(long nConfID, java.lang.String sConfBanner) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddVideoLayout
     */
    public int addVideoLayout(java.lang.String videoLayoutXml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetVideoLayoutByLayoutSepecies
     */
    public java.lang.String getVideoLayoutByLayoutSepecies(int sepecies) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DeleteVideoLayout
     */
    public int deleteVideoLayout(int sepecies) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SetClientHttpServerPort
     */
    public int setClientHttpServerPort(int port) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetClientHttpServerPort
     */
    public int getClientHttpServerPort() throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddConfAccessibleIPC
     */
    public int addConfAccessibleIPC(long confid, java.lang.String user, java.lang.String pwd, java.lang.String ip, int port, int extenddata, int streamType, int channel) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__QueryConfAccessibleIPC
     */
    public java.lang.String queryConfAccessibleIPC(long confid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__DelConfIPCDevice
     */
    public int delConfIPCDevice(long confid, java.lang.String ip, java.lang.String user, int channel, int streamtype) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__ConfRecordCtrl
     */
    public int confRecordCtrl(long confid, boolean status, int model, int num) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateAppTheme
     */
    public int updateAppTheme(java.lang.String szXml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SetConfRecordServerInfo
     */
    public int setConfRecordServerInfo(java.lang.String ip, int port) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetConfRecordServerInfo
     */
    public void getConfRecordServerInfo(javax.xml.rpc.holders.StringHolder ip, javax.xml.rpc.holders.IntHolder port) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__SetConrRecordConfig
     */
    public int setConrRecordConfig(int resolution, int rate, int stream, java.lang.String kind, int count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetConfRecordConfig
     */
    public void getConfRecordConfig(javax.xml.rpc.holders.StringHolder kind, javax.xml.rpc.holders.IntHolder resolution, javax.xml.rpc.holders.IntHolder rate, javax.xml.rpc.holders.IntHolder stream, javax.xml.rpc.holders.IntHolder count) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__AddNoticeToUser
     */
    public java.lang.String addNoticeToUser(java.lang.String sMsg, java.lang.String sUserXml) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__NoticeConfMember
     */
    public int noticeConfMember(long nConfID, java.lang.String sMsg) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetConfSingleRecordVideos
     */
    public java.lang.String getConfSingleRecordVideos(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetConfMixRecordVideos
     */
    public java.lang.String getConfMixRecordVideos(long orgid) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__UpdateVideoDelInfo
     */
    public int updateVideoDelInfo(java.lang.String sCheckTime, int nSingleDelDays, int nMixDelDays, int nSingleMinSpace, int nMixMinSpace) throws java.rmi.RemoteException;

    /**
     * Service definition of function ns__GetVideoDelInfo
     */
    public java.lang.String getVideoDelInfo() throws java.rmi.RemoteException;
}
