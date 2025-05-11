CREATE DATABASE IF NOT EXISTS `portal_wifi_audit`;
CREATE DATABASE IF NOT EXISTS `portal_wifi_data`;

CREATE USER 'austerdb'@'%' IDENTIFIED BY 'auster123';
GRANT ALL PRIVILEGES ON *.* TO 'austerdb'@'%';
