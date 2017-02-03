job('seed') {
    scm {
        github 'saurau01/SeedJob'
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        
        dsl {
            external '*Job*.groovy'
            
        }
    }
}
