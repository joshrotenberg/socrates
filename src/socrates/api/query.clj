(ns socrates.api.query
  (:use socrates.core
        socrates.util)
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.contrib.zip-filter.xml :as zf])
  (:import java.io.ByteArrayInputStream))

(defn query-parser
  [body]
    (let [zipped (-> (ByteArrayInputStream. body)
                   (xml/parse)
                   (zip/xml-zip))]
      (println body)
      body))

(defn query
  [& args]
  (let [request-uri (str "http://" *api-url* "/query")
        split-args (split-positional-args args)
        question (apply str (first split-args))
        query-params (second split-args)
        auth {:api_account_id *api-account-id* :api_password *api-password*}
        request (prepare-request request-uri question query-params auth)]
    (direct-answer-parser (execute-request request))))
                         
