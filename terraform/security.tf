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
    cidr_blocks = ["0.0.0.0/0"]
    protocol    = "tcp"
    description = "https"
    from_port   = 443
    to_port     = 443
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

data "aws_iam_policy_document" "ecs_agent" {
  statement {
    actions = ["sts:AssumeRole"]

    principals {
      type        = "Service"
      identifiers = ["ec2.amazonaws.com"]
    }
  }
}

resource "aws_iam_role" "ecs_agent" {
  name               = "ecs-agent"
  assume_role_policy = data.aws_iam_policy_document.ecs_agent.json
}

resource "aws_iam_role_policy_attachment" "ecs_agent" {
  role       = aws_iam_role.ecs_agent.name
  policy_arn = "arn:aws:iam::aws:policy/service-role/AmazonEC2ContainerServiceforEC2Role"
}

resource "aws_iam_instance_profile" "ecs_agent" {
  name = "ecs-agent"
  role = aws_iam_role.ecs_agent.name
}
