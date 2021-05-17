# https://engineering.finleap.com/posts/2020-02-20-ecs-fargate-terraform/
terraform {
  backend "s3" {
    bucket  = "terraformbucketmattm123"
    key     = "terraform.tfstate"
    region  = "us-east-2"
    profile = "iamterraform"
  }
}

provider "aws" {
  region  = "us-east-2"
  profile = "iamterraform"
}

