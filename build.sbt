lazy val root = (project in file("."))
  .enablePlugins(DockerRunPlugin)
  .settings(
    scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
    dockerContainers := Seq(
      // Define a container for mysql
      DockerContainer(
        id = "myapp_mysql",
        name = "mysql",
        version = "5.7",
        ports = Seq(
          3306 `:` 3306
        ),
        environment = Map(
          "MYSQL_ROOT_PASSWORD" -> "s3cr3t",
          "MYSQL_DATABASE"      -> s"db_myapp",
          "MYSQL_USER"          -> "db",
          "MYSQL_PASSWORD"      -> "123456"
        ),
        volumes = Map(
          file("./data/mysql") -> "/var/lib/mysql"
        )
      )
    )
  )
