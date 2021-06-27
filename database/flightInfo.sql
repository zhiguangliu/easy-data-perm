CREATE TABLE `flight_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from` varchar(3) NOT NULL,
  `from_area` varchar(10) NOT NULL,
  `to` varchar(3) NOT NULL,
  `to_area` varchar(10) NOT NULL,
  `company` varchar(10) NOT NULL,
  `flight_number` varchar(10) not null,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'TSN', 'N', 'CA', 'CA0001');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'SHE', 'NE', 'CA', 'CA0002');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'HRB', 'NE', 'CA', 'CA0007');

INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'TSN', 'N', 'MU', 'MU0001');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'SHE', 'NE', 'MU', 'MU0002');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'PEK', 'NC', 'HRB', 'NE', 'MU', 'MU0007');


INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'PEK', 'N', 'CA', 'CA0003');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'SHE', 'NE', 'CA', 'CA0004');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'HRB', 'NE', 'CA', 'CA0008');


INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'PEK', 'N', 'MU', 'MU0003');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'SHE', 'NE', 'MU', 'MU0004');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'TSN', 'NC', 'HRB', 'NE', 'MU', 'MU0008');


INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'NE', 'PEK', 'N', 'CA', 'CA0005');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'N', 'TSN', 'N', 'CA', 'CA0006');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'N', 'HRB', 'NE', 'CA', 'CA0009');


INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'NE', 'PEK', 'N', 'MU', 'MU0005');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'N', 'TSN', 'N', 'MU', 'MU0006');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'SHE', 'N', 'HRB', 'NE', 'MU', 'MU0009');

INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'PEK', 'N', 'CA', 'CA0010');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'TSN', 'N', 'CA', 'CA0011');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'SHE', 'NE', 'CA', 'CA0012');

INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'PEK', 'N', 'MU', 'MU0010');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'TSN', 'N', 'MU', 'MU0011');
INSERT INTO `flight_info` ( `from`, `from_area`, `to`, `to_area`, `company`, `flight_number`) VALUES ( 'HRB', 'NE', 'SHE', 'NE', 'MU', 'MU0012');