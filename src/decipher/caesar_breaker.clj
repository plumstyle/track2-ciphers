(ns decipher.caesar-breaker)

(defn to-int
  "takes a encrypted char and converts it to a integer"
  [char]
  (- (int char) 97))

(defn to-char
  "takes a integer and converts it to a character"
  [int]
  (char (+ int 97)))

(defn to-string
  "takes a vector of integers and converts it to a string (plaintext)"
  [plainints]
  (->> plainints
       (map to-char)
       (apply str)))

(defn modular-add
  "takes a integer and adds key value to it"
  [int key]
  (let [position (+ (mod key 26) int)]
    (if (> position 26)
        (- position 26)
        position)))

(defn break-caesar
  "takes a ciphertext and a number, then returns its deciphered text"
  [ciphertext key]
  (to-string
    (mapv #(modular-add % key)
          (mapv to-int ciphertext))))
