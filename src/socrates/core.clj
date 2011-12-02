(ns socrates.core
  (:use socrates.util)
  (:require [clj-http.client :as cclient]))

(def ^:dynamic *client-version* (System/getProperty "socrates.version"))
(def ^:dynamic *api-account-id* nil)
(def ^:dynamic *api-password* nil)

(def ^:dynamic *api-url* "api.trueknowledge.com")
(def ^:dynamic *api-version* "stable")
(def user-agent (str "socrates/" *client-version*))


(defmacro with-credentials
  "Use the True Knowledge API Account ID and Password."
  [id password & body]
  `(binding [*api-account-id* ~id
             *api-password* ~password]
     (do 
       ~@body)))

(defn execute-request 
  "Executes the HTTP request and handles the response"
  [request]
  (let [response (cclient/request request)
        status (:status response)
        body (:body response)
        headers (:headers response)]
    (cond
     (status-is-client-error status) (throw (Exception. "Client error"))
     (status-is-server-error status) (throw (Exception. "Server error"))
     :else
     body)))

(defn prepare-request 
  "Prepares the HTTP request"
  [request-uri question query-params auth]
  {:method :get
   :url request-uri
   :query-params (merge query-params auth {:question question})
   :headers {"User-Agent" user-agent}})

(defn direct-answer
  [& args]
  (let [request-uri (str "http://" *api-url* "/direct_answer/" *api-version*)
        split-args (split-positional-args args)
        question (apply str (first split-args))
        query-params (second split-args)
        auth {:api_account_id *api-account-id* :api_password *api-password*}
        request (prepare-request request-uri question query-params auth)]
    (prn request)
    (execute-request request)))
                         


