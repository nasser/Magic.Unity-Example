(ns boids
  (:import [UnityEngine GameObject Time Transform Vector3 Physics]))

(defmacro v3+
  ([a b] `(Vector3/op_Addition ~a ~b))
  ([a b & rest] `(Vector3/op_Addition ~a (v3+ ~b ~@rest))))

(defmacro v3-
  ([a b] `(Vector3/op_Subtraction ~a ~b))
  ([a b & rest] `(Vector3/op_Subtraction ~a (v3- ~b ~@rest))))
(defmacro v3*
  ([a b] `(Vector3/op_Multiply ~a ~b))
  ([a b & rest] `(Vector3/op_Multiply ~a (v3* ~b ~@rest))))

(defn separation [^Transform boid ^UnityEngine.Transform|[]| boids ^float space]
  (loop [i (int 0)
         d (Vector3. 0 0 0)]
    (if (< i (.Length boids))
      (let [b (aget boids i)
            bpos (.position b)
            boidpos (.position boid)
            distance (Vector3/Distance boidpos bpos)]
        (if (< distance space)
          (recur (inc i) (v3+ d (v3- boidpos bpos)))
          (recur (inc i) d)))
      d)))

(defn cohesion [^Transform boid ^UnityEngine.Transform|[]| boids]
  (loop [i (int 0)
         d (Vector3. 0 0 0)]
    (if (< i (.Length boids))
      (let [b (aget boids i)
            bpos (.position b)
            boidpos (.position boid)]
        (recur (inc i) (v3+ d (v3* (v3- bpos boidpos) 0.05))))
      d)))

(defn alignment [^Transform boid ^UnityEngine.Transform|[]| boids]
  (loop [i (int 0)
         d (Vector3. 0 0 0)]
    (if (< i (.Length boids))
      (let [b (aget boids i)
            boidfwd (.forward boid)]
        (recur (inc i) (v3+ d (v3* boidfwd 0.5))))
      d)))

(defn flock [^Transform boid ^UnityEngine.Transform|[]| boids]
  (let [sep (separation boid boids 2)
        coh (cohesion boid boids)
        ali (alignment boid boids)]
    (v3+ sep coh ali)))

(defmacro transforms [arry]
  `(let [arry# ~arry
         trns# (make-array UnityEngine.Transform (count arry#))]
     (loop [i# (int 0)]
       (if (< i# (.Length arry#))
         (do
           (aset trns# i# (.transform (aget arry# i#)))
           (recur (inc i#)))
         trns#))))

(defn update* [^Transform b]
  (let [neighbours (transforms (Physics/OverlapSphere (.position b) 5))
        p* (Vector3/Lerp (.position b) (flock b neighbours) Time/deltaTime)
        fwd* (Vector3/Lerp (.forward b) (v3- p* (.position b)) 0.75)]
    (set! (.position b) p*)
    (set! (.forward b) fwd*)))

(defn update-boid [^GameObject go]
  (update* (.transform go)))