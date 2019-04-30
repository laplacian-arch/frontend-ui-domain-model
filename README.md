# laplacian-arch:model:client-app-arch

An architechture model of client applications which runs on certain devices and consumes APIs.
This model assumes that the client state is managed with Redux.



## The structure of the *client-app-arch* model

The following diagram summarizes the structure of the model:

![](./doc/image/model-diagram.svg)


As shown in above diagram, this model depends on the following external models.

- *metamodel* (laplacian.model.metamodel)

- *service-api-arch* (laplacian-arch.model.service-api-arch)



## Getting started

Firstly, you need to add the following entry to your `laplacian-module.yml` :

```yaml
project:
  group: ${your.project.group}
  name: ${your.project.name}
  type: project
  version: "1.0.0"
  models:
  ## ↓↓ ADD ↓↓ ##
  - group: laplacian-arch
    name: client-app-arch
    version: "1.0.0"
  ## ↑↑ ADD ↑↑ ##
```

To reflect the change, you need to type the following command in your console :
```bash
./gradlew lM
```

Then put some template files under the *./template* directory and type the following command to generate files:
```bash
./gradlew lG
```