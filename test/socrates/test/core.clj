(ns socrates.test.core
  (:use socrates.core)
  (:use clojure.test
        socrates.test.properties))

(deftest replace-me ;; FIXME: write
  (with-credentials *trueknowledge-account-id* *trueknowledge-api-password*
  (prn (direct-answer "List of James Bond Actors"))))
