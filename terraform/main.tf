# https://medium.com/warp9/get-started-with-aws-ecs-cluster-using-terraform-cfba531f7748


data "aws_region" "current_region" {}

terraform {
  backend "s3" {
    bucket = "terraformbucketmattm123"
    key    = "terraform.tfstate"
    region = "us-east-2"
  }
}

provider "aws" {
  region = "us-east-2"
}


resource "aws_vpc" "terraform_learning_vpc" {
  cidr_block           = "10.0.0.0/22"
  enable_dns_support   = true
  enable_dns_hostnames = true
}

