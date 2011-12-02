(ns socrates.api.direct-answer
  (:use socrates.core
        socrates.util)
  (:require [clojure.xml :as xml]
            [clojure.zip :as zip]
            [clojure.contrib.zip-filter.xml :as zf])
  (:import java.io.ByteArrayInputStream))

(defn da-parse
  [body]
  (let [zipped (-> (ByteArrayInputStream. body)
                   (xml/parse)
                   (zip/xml-zip))
        tk-top (zf/xml1-> zipped)
        tk-answered (zf/attr tk-top :answered)
        tk-text-result (zf/xml1-> zipped :tk:text_result zf/text)]
    {:answered (if (= "true" tk-answered)
                 true
                 false)
     :result tk-text-result}))
    
(defn direct-answer
  [& args]
  (let [request-uri (str "http://" *api-url* "/direct_answer/" *api-version*)
        split-args (split-positional-args args)
        question (apply str (first split-args))
        query-params (second split-args)
        auth {:api_account_id *api-account-id* :api_password *api-password*}
        request (prepare-request request-uri question query-params auth)]
    (da-parse (execute-request request))))
                         
