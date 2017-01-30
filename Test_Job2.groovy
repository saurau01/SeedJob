mavenJob('OneClickDeployment/Test_Job2') {
 scm {
     github('assahoo/mvn-test', 'master')
     }
 triggers {
           upstream('OneClickDeployment/compile_Job1', 'SUCCESS')
          }
 rootPOM('pom.xml')
													    
        goals('test')
}