INSERT INTO `dp_permission`(`id`, `metadata_id`, `permission_name`, `status`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 1, 'perm-1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission`(`id`, `metadata_id`, `permission_name`, `status`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, 1, 'perm-2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item`(`id`, `permission_id`, `metadata_id`, `relation`, `value_type`, `field_value`, `apply_method`, `target_table_name`, `field_name`, `field_type`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 1, 1, 'EQUAL', 'CONSTANT', 'aa,bb', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item`(`id`, `permission_id`, `metadata_id`, `relation`, `value_type`, `field_value`, `apply_method`, `target_table_name`, `field_name`, `field_type`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, 1, 2, 'NOT_CONTROL', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item`(`id`, `permission_id`, `metadata_id`, `relation`, `value_type`, `field_value`, `apply_method`, `target_table_name`, `field_name`, `field_type`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (3, 2, 1, 'EQUAL', 'ALL_VALUE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item`(`id`, `permission_id`, `metadata_id`, `relation`, `value_type`, `field_value`, `apply_method`, `target_table_name`, `field_name`, `field_type`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (4, 2, 2, 'NOT_EQUAL', 'CONSTANT', '133', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item_metadata`(`id`, `permission_metadata_id`, `target_table_name`, `field_name`, `field_desc`, `field_type`, `status`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 1, 'data_table', 'test_field_1', 'test_field_1', 'STRING', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_item_metadata`(`id`, `permission_metadata_id`, `target_table_name`, `field_name`, `field_desc`, `field_type`, `status`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, 1, 'data_table', 'test_field_2', 'test_field_2', 'NUMBER', '99', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_permission_metadata`(`id`, `sub_system_code`, `operation_name`, `operation_identifier`, `class_name`, `status`, `matching_mode`, `apply_method`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 'TEST', 'OP-1', 'OP-1', NULL, NULL, 'LENIENT', 'EMBED', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role`(`id`, `p_id`, `sub_system_code`, `type`, `role_name`, `status`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, NULL, 'TEST', NULL, 'ROLE-1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role`(`id`, `p_id`, `sub_system_code`, `type`, `role_name`, `status`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, NULL, 'TEST', NULL, 'ROLE-2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_permission_relation`(`id`, `role_id`, `permission_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 2, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_permission_relation`(`id`, `role_id`, `permission_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_user`(`id`, `sub_system_code`, `user_id`, `user_name`, `role_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (1, 'TEST', '2', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_user`(`id`, `sub_system_code`, `user_id`, `user_name`, `role_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (2, 'TEST', '1', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_user`(`id`, `sub_system_code`, `user_id`, `user_name`, `role_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (3, 'TEST', '3', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `dp_role_user`(`id`, `sub_system_code`, `user_id`, `user_name`, `role_id`, `memo`, `create_time`, `create_user_id`, `create_user_name`, `update_time`, `update_user_id`, `update_user_name`) VALUES (4, 'TEST', '3', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
