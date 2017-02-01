mavenJob('OneClickDeployment/Build_Job3') {
 scm {
     github('saurau01/OneClickDemo', 'master')
     }
 triggers {
           upstream('OneClickDeployment/Test_Job2', 'SUCCESS')
		   
          }
 rootPOM('pom.xml')
													    
 goals('package')
 publishers {
        archiveArtifacts('target/*.war')
		
        downstream('OneClickDeployment/Deploy_Job4', 'SUCCESS')
        s3('s3upload') {
            entry('target/*.war', 'vagrant-jenkins', 'us-east-1') {
                storageClass('STANDARD')
                noUploadOnFailure()
                
            }
        }
    
    }
}