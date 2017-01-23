buildPipelineView('OCD') {
    filterBuildQueue()
    filterExecutors()
    title('One Click Deployment CI Pipeline')
    displayedBuilds(1)
    selectedJob('OneClickDeployment/compile_Job1')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
}

mavenJob('OneClickDeployment/compile_Job1') {
 scm {
	    github('saurau01/OneClickDemo', 'master')
	        }
triggers {
          snapshotDependencies(true)
          authenticationToken('capgemini')
         }
rootPOM('pom.xml')
							    
goals('compile')
}


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
    
    }
}

freeStyleJob('OneClickDeployment/Deploy_Job4') {
    
    
    steps {
        shell(''' #!/bin/sh
                 export HOME=/root 
                 if dpkg -l | grep vagrant > /dev/null; then
                 sudo apt-get install vagrant
                 fi
		         #vagrant up --provider=aws
		         #/usr/bin/vagrant provision'''
             )
	    
    }
}