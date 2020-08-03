using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using Magic.Unity;
using clojure.lang;

public class Boid : MonoBehaviour
{
    Var UpdateBoid;
    
    void Awake()
    {
        Clojure.Require("boids");
        UpdateBoid = Clojure.GetVar("boids", "update-boid");
    }

    void Update()
    {
        UpdateBoid.invoke(gameObject);
    }
}
