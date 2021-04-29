resource "aws_ecr_repository" "worker" {
  name = "worker"
}

resource "aws_ecs_cluster" "ecs_cluster" {
  name = "terraform-learning-cluster"
}

data "template_file" "task_definition_template" {
  template = file("${path.module}/task_definition.json.tpl")
  vars = {
    REPOSITORY_URL = replace(aws_ecr_repository.worker.repository_url, "https://", "")
  }
}

resource "aws_ecs_task_definition" "task_definition" {
  family                = "worker"
  container_definitions = data.template_file.task_definition_template.rendered
}

resource "aws_ecs_service" "worker" {
  name            = "worker"
  cluster         = aws_ecs_cluster.ecs_cluster.id
  task_definition = aws_ecs_task_definition.task_definition.arn
  desired_count   = 2
}
