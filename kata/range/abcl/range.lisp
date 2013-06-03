(defun allPoints (range)
  (let* ((c (position #\, range))
         (start0 (parse-integer (subseq range 1 c)))
  	 (end0 (parse-integer (subseq range (1+ c) (1- (length range)))))
	 (start (if (char= #\[ (elt range 0)) start0 (1+ start0)))
	 (end (if (char= #\] (elt range (1- (length range)))) (1+ end0) end0))
         (output (make-array (- end start) :fill-pointer 0)))
    (dotimes (i (- end start))
      (vector-push (+ start i) output))
    output))

(defun contains (range point) 
  (some #'(lambda (point2) (= point2 point)) (allPoints range)))

(defun containsRange (range1 range2)
  (every #'(lambda (point) (contains range1 point)) (allPoints range2)))

(defun overlaps (range1 range2)
  (some #'(lambda (point) (contains range1 point)) (allPoints range2)))
