
terraform {
  required_providers {
    heroku = {
      source = "heroku/heroku"
      version = "4.6.0"
    }
  }
}

provider "heroku" {

    
  email = "oussama.issaoui1@esprit.tn"
  api_key = "${var.heroku_api_key}"
}

resource "heroku_app" "default" {
  name   = "${var.name}"
  region = "eu"  # Or "us"
  stack  = "container"
}

