# Very simple Akka-http and flyway project

Since Tuesday, 2nd August, I cannot deploy anymore a Scala SBT project with the flyway DB SBT plugins.
It used to work for more than 6 months, but our build on [Clevercloud](https://www.clever-cloud.com) stopped to work last Tuesday.

[Flyway](https://flywaydb.org/) is an open-source database migration tools. We use the SBT plugin to apply database migration to our applications.

The clevercloud support team is also looking at this issue.

# The issue

When I deploy this project on Clever-cloud, the flyway SBT plugin is not loaded, we have an *handshake_failure* wich is usually related to SSL issues (but not always). 

    2016-08-04T16:58:39.222+02:00[info] Resolving org.flywaydb#flyway-sbt;4.0.3 ...
    2016-08-04T16:58:39.222+02:00#015#033M[info] Resolving org.flywaydb#flyway-sbt;4.0.3 ...
    2016-08-04T16:58:41.523+02:00[error] Server access Error: Received fatal alert: handshake_failure url=https://flywaydb.org/repo/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom
    2016-08-04T16:58:41.523+02:00[warn] module not found: org.flywaydb#flyway-sbt;4.0.3
    2016-08-04T16:58:41.524+02:00[warn] /home/bas/.ivy2/local/org.flywaydb/flyway-sbt/scala_2.10/sbt_0.13/4.0.3/ivys/ivy.xml
    2016-08-04T16:58:41.524+02:00[warn] ==== public: tried
    2016-08-04T16:58:41.524+02:00[warn] https://repo1.maven.org/maven2/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom
    2016-08-04T16:58:41.524+02:00[warn] ==== sbt-plugin-releases: tried
    2016-08-04T16:58:41.524+02:00[warn] https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/org.flywaydb/flyway-sbt/scala_2.10/sbt_0.13/4.0.3/ivys/ivy.xml
    2016-08-04T16:58:41.524+02:00[warn] ==== local: tried
    2016-08-04T16:58:41.525+02:00[warn] ==== Flyway: tried
    2016-08-04T16:58:41.526+02:00[warn] https://flywaydb.org/repo/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom
    
However, https://flywaydb.org/repo/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom is valid and can be loaded
     
     wget -S https://flywaydb.org/repo/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom
     
     --2016-08-04 17:05:02--  https://flywaydb.org/repo/org/flywaydb/flyway-sbt_2.10_0.13/4.0.3/flyway-sbt-4.0.3.pom
    Resolving flywaydb.org... 104.31.84.189, 104.31.85.189
    Connecting to flywaydb.org|104.31.84.189|:443... connected.
    HTTP request sent, awaiting response... 
      HTTP/1.1 200 OK
      Date: Thu, 04 Aug 2016 15:05:03 GMT
      Content-Type: application/octet-stream
      Content-Length: 1316
      Connection: keep-alive
      Set-Cookie: __cfduid=d853e6d0de77657763ca47a477acefc101470323103; expires=Fri, 04-Aug-17 15:05:03 GMT; path=/; domain=.flywaydb.org; HttpOnly
      Last-Modified: Thu, 14 Jul 2016 15:23:19 GMT
      Access-Control-Allow-Origin: *
      Expires: Thu, 04 Aug 2016 15:15:03 GMT
      Cache-Control: max-age=600
      Accept-Ranges: bytes
      X-GitHub-Request-Id: 6CA2E5C0:252F:7B1E44A:57A3599F
      Server: cloudflare-nginx
      CF-RAY: 2cd2e7c32cd908e4-CDG
    Length: 1316 (1.3K) [application/octet-stream]
    Saving to: 'flyway-sbt-4.0.3.pom'
    
    flyway-sbt-4.0.3.pom                               100%[=============================================================================================================>]   1.29K  --.-KB/s    in 0s      
    
    2016-08-04 17:05:03 (9.80 MB/s) - 'flyway-sbt-4.0.3.pom' saved [1316/1316]
    
    
It seems that there's an issue with HTTPs or maybe the SSL certificate from flywaydb.org
    
# References
    
See [A Stackoverflow error with flyway-sbt](http://stackoverflow.com/questions/37980246/cannot-resolve-flyway-sbt-with-handshake-failure-on-centos7]
See also [Flyway quick how-to](https://flywaydb.org/getstarted/firststeps/sbt)
[An issue on flyway with the artefacts](https://github.com/flyway/flyway/issues/1276)
    