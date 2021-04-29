resource "aws_db_subnet_group" "mysql_subnet_group" {
  name       = "mysql-subnet-group"
  subnet_ids = [aws_subnet.pub_subnet.id, aws_subnet.pub_subnet2.id]
}

resource "aws_db_instance" "mysql" {
  identifier                = "mysql"
  allocated_storage         = 5
  backup_retention_period   = 2
  backup_window             = "01:00-01:30"
  maintenance_window        = "sun:03:00-sun:03:30"
  multi_az                  = true
  engine                    = "mysql"
  engine_version            = "5.7"
  instance_class            = "db.t2.micro"
  name                      = "worker_db"
  username                  = "worker"
  password                  = "mypassword123"
  port                      = 3306
  db_subnet_group_name      = aws_db_subnet_group.mysql_subnet_group.name
  vpc_security_group_ids    = [aws_security_group.rds_sg.id, aws_security_group.ecs_sg.id]
  skip_final_snapshot       = true
  final_snapshot_identifier = "worker-final"
  publicly_accessible       = true
}
