(ns socrates.test.api.direct-answer
  (:use socrates.core
        socrates.api.direct-answer)
  (:use clojure.test
        socrates.test.properties))

(deftest direct-answer-test
  (with-credentials *trueknowledge-account-id* *trueknowledge-api-password*
    (let [bonds (direct-answer "List of James Bond Actors")
          banana (direct-answer "What color is a banana?")]
      (is (= true (:answered bonds)))
      (is (= "Sean Connery, George Lazenby, Roger Moore, Timothy Dalton, Pierce Brosnan and Daniel Craig" (:result bonds)))
      (is (= true (:answered banana)))
      (is (= "yellow, (the colour)" (:result banana))))))