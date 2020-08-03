# Magic.Unity-Example

A sample project demonstrating the [Magic.Unity](https://github.com/nasser/Magic.Unity) integration.

For convenience, it includes the [1.0 release of Magic.Unity](https://github.com/nasser/Magic.Unity/releases/tag/v1.0-beta).

## Notable files

* **`Assets/boids.clj`** The Clojure implementation of [the boids algorithm](https://en.wikipedia.org/wiki/Boids). It is compiled into the `MagicBuild/boids.clj.dll` file and can be recompiled using The procedure described in the [Magic.Unity README](https://github.com/nasser/Magic.Unity/blob/master/README.md).

* **`Assets/Boid.cs`** The Unity component script that is attached to GameObjects in the scene. It looks up and invokes the `boids/update-boid` implemented in `boids.clj`.

* **`Assets/Scenes/SampleScene.unity`** The scene, combining the Boid comopnent and GameObjects.

Legal
-----
Copyright © 2020 Ramsey Nasser and contributors

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

```
http://www.apache.org/licenses/LICENSE-2.0
```

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
