enablePlugins(JavaAppPackaging)
maintainer in Docker := "Edd Steel <edward.steel@gmail.com>"
packageName in Docker := "eddsteel/views"
packageSummary in Docker := "Reviews of things (to try out a modern scala stack)"
packageDescription := "Reviews of things (to try out a modern scala stack)"
dockerExposedPorts := List(8080)
dockerBaseImage := "frolvlad/alpine-scala"
dockerUpdateLatest := true
