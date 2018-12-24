# Up

Up is a Gradle Plugin for versioning and publishing Android apps.

## Description

Simple DSL for defining how the app should be *built*, *versioned* and *published*. As a result the plugin creates the proper gradle tasks for executing these operations.

### Tasks
Up prefixed tasks have 3 stages: version, build and publish. Depending on the plugin DSL configuration and passed options some of these may or may not get executed.

The plugin generates the following tasks
* __upApk__ - Upload the given apk to passed destination
* __up{variant}__ - Execute the stages from above for the given variant / flavour.

### Options
Options control what stages get executed and how
* __--to__:
    description: "Destination for publishing"
    values: "play", "appcenter", "slack", "email", "nexus", "dir", "ftp"

* __--version__:
    description: "Increment the current version by adding 1 to the passed value. If set to 'eq=' sets the version to the passed value."
    values: "major", "minor", "revision", "eq="

* __--path__:
    description: "Path to the apk file to be uploaded. Option removes --version option and uses --build=false

* __--build__:
    description: Set to false if an existing apk should be used. If false, not compatible with version
    values: "false", "true"

## Installation

```
implementation 'TOBEADDED'
```

## Getting Started

To be added properly. For now writing only 2 types of DSL configurations
* Possibilities
```
apply plugin "up"
up {
    version {
        stage = [
            debug: 1
            release: 2
        ]
        major = "1"
        minor = "0"
        revision = "4"
        scheme = "{major}.{minor}.{revision}.{stage}"
        
        git {
            tag = true
            commit = true
            path = "path/to/git"        
        }                    
    }

    build {
        command = "gradlew customAssemble"    
        assemble = false
    }

    publish {
        play {
            user = "whateveruser"
            password = "whateverpassword"
        }
        appcenter {
            token = "whatevertoken"
            appId = "whateverappid"
            appSecret = "whateversecret"
        }
        slack {
            token = "whatevertoken"
            channel = "mchannel"
        }
        email {
            author = "Up Plugin"
            target = "group@mail.com"
        }
        dir {
            path = "/path/to/where/i/want/the/app/to/be/put
            name = "my-nice-app.apk"             
        }
        nexus {
            ...
        }
    }
}
```
* Typical plugin usage

Configure
```
up {
    publish {
        appcenter {
            token = "whatevertoken"
            appId = "whateverappid"
            appSecret = "whateversecret"
            variants = [
                staging = {
                    appId = "stagingappid"
                }            
            ]
        }
    }
}
```

And then
```
gradlew upStaging
```

## Usage

To be added properly. For now writing only what I want it to work like:

#### Build and upload "staging" build variant to Google Play 

```
gradlew upStaging --to=play
```

#### Increment version with a minor, build and upload "paidStaging" build variant to AppCenter

```
gradlew upPaidStaging --to=appcenter --version=minor
```

#### Send "paidStaging" build to email, without building the apk, and just uploading the apk found at the normal path of a paid staging variant

```
gradlew upPaidStaging --to=email --build=false
```

#### Build and upload "freeRelease" build variant to slack, and a directory

```
gradlew upFreeRelease --to=slack,dir
```

#### Upload the apk from the given path to a slack channel

```
gradlew upApk --path="/path/to/apk" --to=slack
```

## Roadmap
This project will start with only some implementations for upload destinations and versioning, and will evolve continuosly.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
