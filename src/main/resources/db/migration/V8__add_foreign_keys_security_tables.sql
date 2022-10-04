ALTER TABLE app_user_app_user_role ADD FOREIGN KEY (app_user_id) REFERENCES app_user(id);
ALTER TABLE app_user_app_user_role ADD FOREIGN KEY (app_user_role_id) REFERENCES app_user_role(id);


