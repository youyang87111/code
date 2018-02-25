/*�����������ӳ���������һ����ͼV_B_BRANCHMAP*/
create or replace view V_B_BRANCHMAP as 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('01' USING gbk) bmf,null cardtype,null amt1,null amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from T_B_BRANCHMAP 
union all 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('02' USING gbk) bmf,cardtype cardtype,null amt1,null amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_CARDTYPE
union all 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('03' USING gbk) bmf,null cardtype,amt1 amt1,amt2 amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_AMT
union all 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('04' USING gbk) bmf,cardtype cardtype,amt1 amt1,amt2 amt2,null issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_CARDTYPE_AMT
union all 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('06' USING gbk) bmf,null cardtype,null amt1,null amt2,issuerid issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_ISSUER
union all 
select rid,lbid,lmcr,lmid,class,rbid,CONVERT('05' USING gbk) bmf,cardtype cardtype,null amt1,null amt2,issuerid issuerid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_BRANCHMAP_ISSUER_CARDTYPE; 

/*��T_B_TERM_MAP_MODE���T_B_TERM_MAP������������ͼ*/
create or replace view V_B_TERMMAP as 
select lbid,lmcr,lmid,rbid,rmcr,rmid,tmf tmf,' ' ltid,' ' rtid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from T_B_TERM_MAP_MODE where tmf='06' 
union all 
select lbid,lmcr,lmid,rbid,rmcr,rmid,'01' tmf,ltid ltid,rtid rtid,status,auditstatus,buildoper,builddatetime,modifyoper,modifydatetime,auditoper,auditdatetime,version from  T_B_TERM_MAP;
