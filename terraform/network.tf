
resource "aws_internet_gateway" "internet_gateway" {
  vpc_id = aws_vpc.terraform_learning_vpc.id
}

resource "aws_subnet" "pub_subnet" {
  vpc_id            = aws_vpc.terraform_learning_vpc.id
  cidr_block        = "10.0.1.0/24"
  availability_zone = "us-east-2b"
}

resource "aws_subnet" "pub_subnet2" {
  vpc_id            = aws_vpc.terraform_learning_vpc.id
  cidr_block        = "10.0.2.0/24"
  availability_zone = "us-east-2a"
}


resource "aws_route_table" "public" {
  vpc_id = aws_vpc.terraform_learning_vpc.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.internet_gateway.id
  }
}

resource "aws_route_table_association" "route_table_association" {
  subnet_id      = aws_subnet.pub_subnet.id
  route_table_id = aws_route_table.public.id
}

resource "aws_security_group" "ecs_sg" {
  vpc_id = aws_vpc.terraform_learning_vpc.id

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    protocol    = "tcp"
    description = "SSH"
    from_port   = 22
    to_port     = 22
  }

  ingress {
    ipv6_cidr_blocks = ["0.0.0.0/0"]
    protocol         = "tcp"
    description      = "https"
    from_port        = 443
    to_port          = 443
  }

  egress {
    cidr_blocks = ["0.0.0.0/0"]
    protocol    = "tcp"
    description = "ecr"
    from_port   = 0
    to_port     = 65535
  }

}

resource "aws_security_group" "rds_sg" {
  vpc_id = aws_vpc.terraform_learning_vpc.id

  ingress {
    cidr_blocks = ["0.0.0.0/0"]
    protocol    = "tcp"
    description = "MySQL"
    from_port   = 3306
    to_port     = 3306
  }

  egress {
    cidr_blocks = ["0.0.0.0/0"]
    protocol    = "tcp"
    description = "ecr"
    from_port   = 0
    to_port     = 65535
  }
}
