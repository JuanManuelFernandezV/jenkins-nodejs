job('Aplicacion Node.js Docker DSL') {
    description('Aplicación Node JS Docker DSL para el curso de Jenkins')
    scm {
        git('https://github.com/JuanManuelFernandezV/jenkins-nodejs.git', 'master') { node ->
            node / gitConfigName('JuanManuelFernandezV')
            node / gitConfigEmail('juanm2003fernandez@gmail.com')
        }
    }
    triggers {
        scm('H/7 * * * *')
    }
    wrappers {
        nodejs('nodejs')
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('JuanManuelFernandezV/jenkins-nodejs')
            tag('${GIT_REVISION,length=7}')
            registryCredentials('docker-hub')
            forcePull(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
