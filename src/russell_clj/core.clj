(ns russell-clj.core
  (:require [clojure.string]
            [clojure.pprint :refer [pprint]]))

(def qwerty " -=qwertyuiop[]asdfghjkl;'zxcvbnm,./_+QWERTYUIOP{}ASDFGHJKL:\"ZXCVBNM<>?")
(def dvorak " []',.pyfgcrl/=aoeuidhtns-;qjkxbmwvz{}\"<>PYFGCRL?+AOEUIDHTNS_:QJKXBMWVZ")
(def qwerty2dvorak (zipmap qwerty dvorak))

(defn convert [s]
  (apply str (map qwerty2dvorak s)))

(defn cycle [c]
  (concat
   (take-while #(not= c %) (drop 1 (iterate qwerty2dvorak c)))
   [c]))

(defn convert [s]
  (apply str (map qwerty2dvorak s)))

(defn str-cycle [s]
  (concat
   (take-while #(not= s %) (drop 1 (iterate convert s)))
   [s]))

(def cycle-length (comp count cycle))
(defn -main [& args]
  (let [target (clojure.string/join " " args)
        result (str-cycle target)]
    (pprint result)
    (println "Took " (count result) "cycles")
    (pprint (zipmap target (map cycle-length target)))))
