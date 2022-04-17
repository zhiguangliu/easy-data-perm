/*==============================================================*/
/* Table: dp_base_property_define                               */
/*==============================================================*/
create table dp_base_property_define
(
   id                   int not null auto_increment comment '主键',
   sub_system_code      varchar(100),
   property_code        varchar(100) not null,
   property_name        varchar(100),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (property_code),
   key AK_KEY_2 (id)
);

/*==============================================================*/
/* Table: dp_base_property_value                                */
/*==============================================================*/
create table dp_base_property_value
(
   id                   int not null comment '主键',
   sub_system_code      varchar(100),
   property_code        varchar(100) not null,
   value_code           varchar(100),
   value_name           varchar(100),
   is_deleted           varchar(1),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id, property_code)
);

/*==============================================================*/
/* Table: dp_permission                                         */
/*==============================================================*/
create table dp_permission
(
   id                   int not null,
   sub_system_code      varchar(100) not null,
   metadata_id          int,
   permission_name      varchar(200),
   status               varchar(20),
   memo                 varchar(500),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id, sub_system_code)
);

/*==============================================================*/
/* Table: dp_permission_item                                    */
/*==============================================================*/
create table dp_permission_item
(
   id                   int not null,
   sub_system_code      varchar(100) not null,
   permission_id        int,
   metadata_id          int,
   relation             varchar(20),
   value_type           varchar(20),
   filed_value          varchar(2000),
   apply_method         varchar(20),
   target_table_name    varchar(100),
   filed_name           varchar(100),
   filed_type           varchar(20),
   memo                 varchar(500),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id, sub_system_code)
);

/*==============================================================*/
/* Table: dp_permission_item_metadata                           */
/*==============================================================*/
create table dp_permission_item_metadata
(
   id                   int not null,
   sub_system_code      varchar(100) not null,
   permission_metadata_id int,
   target_table_name    varchar(100),
   filed_name           varchar(100),
   filed_desc           varchar(500),
   filed_type           varchar(20),
   status               varchar(20),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id, sub_system_code)
);

/*==============================================================*/
/* Table: dp_permission_metadata                                */
/*==============================================================*/
create table dp_permission_metadata
(
   id                   int not null,
   sub_system_code      varchar(100) not null,
   operation_name       varchar(200),
   operation_identifier varchar(400),
   class_name           varchar(500),
   status               varchar(20),
   matching_mode        varchar(20),
   apply_method         varchar(20),
   memo                 varchar(500),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id, sub_system_code)
);

/*==============================================================*/
/* Table: dp_role                                               */
/*==============================================================*/
create table dp_role
(
   id                   int not null auto_increment,
   p_id                 int,
   sub_system_code      varchar(100),
   type                 varchar(10),
   role_name            varchar(100),
   status               varchar(20),
   memo                 varchar(1000),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id)
);

/*==============================================================*/
/* Table: dp_role_permission_relation                           */
/*==============================================================*/
create table dp_role_permission_relation
(
   id                   int not null,
   dp__id               int,
   sub_system_code      varchar(100),
   role_id              int,
   permission_id        int,
   memo                 varchar(500),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id)
);

/*==============================================================*/
/* Table: dp_role_property_relation                             */
/*==============================================================*/
create table dp_role_property_relation
(
   id                   int not null,
   property_code        varchar(100),
   sub_system_code      varchar(100),
   role_id              int,
   property_value_id    int,
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id)
);

/*==============================================================*/
/* Table: dp_role_user                                          */
/*==============================================================*/
create table dp_role_user
(
   id                   int not null auto_increment,
   sub_system_code      varchar(100) comment '分系统编码',
   user_id              varchar(100) comment '用户主键',
   user_name            varchar(100) comment '用户名',
   email                varchar(100) comment '电子邮箱',
   mobile_phone         varchar(20) comment '手机号码',
   role_id              int comment '角色主键',
   memo                 varchar(100) comment '备注',
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (id)
);

/*==============================================================*/
/* Table: dp_sub_system                                         */
/*==============================================================*/
create table dp_sub_system
(
   id                   int not null auto_increment,
   sub_system_code      varchar(100) not null,
   sub_system_name      varchar(100),
   default_matching_mode varchar(20),
   user_info_provider   varchar(100),
   create_time          datetime comment '创建时间',
   create_user_id       varchar(100) comment '创建人主键',
   create_user_name     varchar(100) comment '创建人姓名',
   update_time          datetime comment '修改时间',
   update_user_id       varchar(100) comment '修改人主键',
   update_user_name     varchar(100) comment '修改人姓名',
   primary key (sub_system_code),
   unique key AK_KEY_2 (id)
);

alter table dp_base_property_value add constraint FK_REFERENCE_5 foreign key (property_code)
      references dp_base_property_define (property_code) on delete restrict on update restrict;

alter table dp_permission add constraint FK_REFERENCE_2 foreign key (metadata_id, sub_system_code)
      references dp_permission_metadata (id, sub_system_code) on delete restrict on update restrict;

alter table dp_permission_item add constraint FK_REFERENCE_3 foreign key (metadata_id, sub_system_code)
      references dp_permission_item_metadata (id, sub_system_code) on delete restrict on update restrict;

alter table dp_permission_item_metadata add constraint FK_REFERENCE_1 foreign key (permission_metadata_id, sub_system_code)
      references dp_permission_metadata (id, sub_system_code) on delete restrict on update restrict;

alter table dp_permission_metadata add constraint FK_REFERENCE_4 foreign key (sub_system_code)
      references dp_sub_system (sub_system_code) on delete restrict on update restrict;

alter table dp_role_permission_relation add constraint FK_REFERENCE_8 foreign key (role_id)
      references dp_role (id) on delete restrict on update restrict;

alter table dp_role_permission_relation add constraint FK_REFERENCE_9 foreign key (dp__id, sub_system_code)
      references dp_permission (id, sub_system_code) on delete restrict on update restrict;

alter table dp_role_property_relation add constraint FK_REFERENCE_10 foreign key (property_value_id, property_code)
      references dp_base_property_value (id, property_code) on delete restrict on update restrict;

alter table dp_role_property_relation add constraint FK_REFERENCE_6 foreign key (role_id)
      references dp_role (id) on delete restrict on update restrict;
