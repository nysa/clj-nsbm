(ns clj-nsbm.core
  (:require [clojure.string :as string])
  (:require [clj-nsbm.util :refer :all]))

(declare item-str)
(declare subfolder)
(declare shortcut)

(defn nsbm
  "Given a vector, returns the entire Netscape Bookmark document compiled as a
  string."
  [v]
  (str "<!DOCTYPE NETSCAPE-Bookmark-file-1>"
       "<!--This is an automatically generated file. "
       "It will be read and overwritten. "
       "Do Not Edit! -->"
       "<Title>Bookmarks</Title>"
       "<H1>Bookmarks</H1>"
       "<DL>" (->> v (map item-str) string/join) "</DL>"))

(defn item-str
  [item]
  (if (:url item) (shortcut item) (subfolder item)))

(defn subfolder
  [item]
  (let [kmap {:date :ADD_DATE}]
    (str "<DT>"
         "<H3 FOLDED" (-> item (item-attrs kmap) attrs-str) ">"
         (escape-html (:title item))
         "</H3>"
         "<DL><p>"
         (string/join (map item-str (:children item)))
         "</DL><p>")))

(defn shortcut
  [item]
  (let [kmap {:url :HREF
              :date :ADD_DATE
              :modified :LAST_MODIFIED
              :visited :LAST_VISIT}]
    (str "<DT>"
         "<A" (-> item (item-attrs kmap) attrs-str) ">"
         (escape-html (:title item))
         "</A>")))
