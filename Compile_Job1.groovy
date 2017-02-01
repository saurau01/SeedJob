String repo = 'saurau01/OneClickDemo'
String branch_name = 'master'
mavenJob('OneClickDeployment/compile_Job1')
{
scm {
	    github repo, branch_name
     }
triggers {
          scm 'H/5 * * * *'
         }
rootPOM('pom.xml')
							    
goals('compile')
}