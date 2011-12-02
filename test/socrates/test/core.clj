(ns socrates.test.core
  (:use socrates.core
        socrates.api.direct-answer)
  (:use clojure.test
        socrates.test.properties))

(deftest direct-answer-test
  (with-credentials *trueknowledge-account-id* *trueknowledge-api-password*
    (let [answer (direct-answer "List of James Bond Actors")]
      (is (= true (:answered answer)))
      (is (= "Sean Connery, George Lazenby, Roger Moore, Timothy Dalton, Pierce Brosnan and Daniel Craig" (:result answer))))))
