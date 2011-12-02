(ns socrates.test.properties
  (:import (java.util.Properties)))

;; slurp in properties
(def ^:dynamic *props*
  (into {} (doto (java.util.Properties.)
             (.load (-> (Thread/currentThread)
                       (.getContextClassLoader)
                       (.getResourceAsStream "test.properties"))))))

(def ^:dynamic *trueknowledge-account-id*
  (or (get *props* "trueknowledge.api.account.id")
      (throw (Exception. "supply your True Knowledge API Account ID in resources/test.properties to run the tests"))))

(def ^:dynamic *trueknowledge-api-password*
  (or (get *props* "trueknowledge.api.password")
      (throw (Exception. "supply your True Knowledge API Password in resources/test.properties to run the tests"))))

