###201811**上线
>1.上线时间:待定
>2.上线内容:公告发布工具对接公服 迭代
####
>1.涉及项目:nbl-notice(10.0.1.22,10.0.1.66),nbl-baseinfo(10.0.1.37)

>2.涉及数据库:nbl-oa库
>>1.notice表: 新增字段 PROJECT_NAME 项目名称 ,PROJECT_CREATE_TIME 项目立项时间 ,BID_BOOK_START_TIME 领购开始时间
>>2.新增表

CREATE TABLE `notice_ext` (
  `ID` varchar(32) NOT NULL,
  `NOTICE_ID` varchar(32) NOT NULL COMMENT '公告ID',
  `SUPERVISE_DEPT_NAME` varchar(200) NOT NULL COMMENT '监管名称',
  `SUPERVISE_DEPT_CODE` varchar(18) DEFAULT NULL COMMENT '监管人代码',
  `TENDER_CONTENT` longtext COMMENT '招标内容',
  `PROJECT_SCALE` varchar(1000) DEFAULT NULL COMMENT '项目规模',
  `BIDDING_ELIGIBILITY` longtext COMMENT '投标人资格条件',
  `BID_DOC_REFER_METHOD` varchar(500) DEFAULT NULL COMMENT '投标文件递交方法',
  `SYNDICATED_FLAG` smallint(1) DEFAULT NULL COMMENT '是否支持联合体投标',
  `IS_ONLINE_OPEN` smallint(1) DEFAULT NULL COMMENT '是否在线投标/开标',
  `QUAL_TYPE` varchar(20) DEFAULT NULL COMMENT '评审办法',
  `TENANT_ID` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告扩展表.由于多个字段都是大文本，所以分表\r\n';

CREATE TABLE `notice_company` (
           `ID` varchar(32) NOT NULL,
           `NOTICE_ID` varchar(32) NOT NULL COMMENT '公告id',
           `COMPANY_ID` bigint(20) DEFAULT NULL,
           `NAME` varchar(200) DEFAULT NULL COMMENT '公司名称',
           `CODE_TYPE` varchar(2) DEFAULT NULL COMMENT '公司代码类型',
           `CODE` varchar(18) DEFAULT NULL COMMENT '公司代码',
           `CONTACTOR` varchar(100) DEFAULT NULL COMMENT '联系人/项目负责人',
           `PHONE_NUMBER` varchar(20) DEFAULT NULL COMMENT '联系电话',
           `EMAIL` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
           `ADDRESS` varchar(200) DEFAULT NULL COMMENT ' 联系地址',
           `ORDER_NO` smallint(3) DEFAULT NULL COMMENT ' 中标候选人排名',
           `CANDIDATE_QUALIFICATION` varchar(200) DEFAULT NULL COMMENT '中标候选人响应招标文件的资格能力条件',
           `TIME_LIMIT` smallint(4) DEFAULT NULL COMMENT '中标候选人 工期/交货期/服务期（天）',
           `TYPE` smallint(1) DEFAULT NULL COMMENT ' 类型 1 = 招标人 2=投标人 3=中标候选人',
           `CREATE_TIME` datetime DEFAULT NULL,
           `CREATE_USER_ID` varchar(32) DEFAULT NULL,
           `TENANT_ID` varchar(32) DEFAULT NULL,
           PRIMARY KEY (`ID`)
         ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告-招标人/投标人/中标候选人';    
         
CREATE TABLE `supervise_send_log` (
  `id` varchar(32) NOT NULL,
  `notice_id` varchar(32) DEFAULT NULL,
  `data` longtext,
  `type` varchar(4) DEFAULT NULL COMMENT '项目=1,招标项目=2,标段（包）=3,招标公告=4,变更公告=5,中标候选人公示=6,中标人=7,中标结果公告=8,招标人=9,招标代理机构=10,投标人=11,12 = 10号令招标项目, 13 = 10号令招标公告 , 14 = 10号令资格预审公告 , 15 = 10号令中标候选人公示 , 16 = 10号令中标结果公示',
  `is_send` tinyint(1) DEFAULT NULL,
  `TENANT_ID` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='对接公服发送日志表，只有自助公告';
  


