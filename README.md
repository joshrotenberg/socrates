# socrates

socrates is a simple Clojure client for the [True Knowledge](http://www.trueknowledge.com) [API](http://images.trueknowledge.com/blog/wp-content/uploads/2011/02/tk_api_docs.pdf).

## Usage

in your project.clj

```
[socrates "0.0.1"]
```

```clojure
(ns your.app
   (:use socrates.api.direct-answer))

;; get your api account id and password  from somewhere

(def my-account-id "api_you")
(def my-account-password "eqwjdhq989282")

(with-credentials my-account-id my-account-password
   (let [answer (direct-answer "What color is a banana?")]
     (if (:answered answer)
       (println (:result answer))))) ;; prints "yellow, (the colour)"
```

## License

Copyright (C) 2011 Josh Rotenberg

Distributed under the Eclipse Public License, the same as Clojure.
