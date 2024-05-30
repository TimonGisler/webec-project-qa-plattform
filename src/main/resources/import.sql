INSERT INTO QUESTION (id, title, text) VALUES (NEXT VALUE FOR QUESTION_SEQ, 'Some Title?', 'Description of problem?');
INSERT INTO QUESTION (id, title, text) VALUES (NEXT VALUE FOR QUESTION_SEQ, 'Other Title', 'Other description of problem?');

INSERT INTO TAG (id, name) VALUES (NEXT VALUE FOR TAG_SEQ, 'Java');
INSERT INTO TAG (id, name) VALUES (NEXT VALUE FOR TAG_SEQ, 'Spring');
INSERT INTO TAG (id, name) VALUES (NEXT VALUE FOR TAG_SEQ, 'HTML');

-- Associate the first question with the "Java" and "Spring" tags
INSERT INTO QUESTION_TAGS (QUESTION_ID, TAGS_ID) VALUES ((SELECT id FROM QUESTION WHERE title = 'Some Title?'), (SELECT id FROM TAG WHERE name = 'Java'));
INSERT INTO QUESTION_TAGS (QUESTION_ID, TAGS_ID) VALUES ((SELECT id FROM QUESTION WHERE title = 'Some Title?'), (SELECT id FROM TAG WHERE name = 'Spring'));

-- Associate the second question with the "HTML" tag
INSERT INTO QUESTION_TAGS (QUESTION_ID, TAGS_ID) VALUES ((SELECT id FROM QUESTION WHERE title = 'Other Title'), (SELECT id FROM TAG WHERE name = 'HTML'));

-- Add some answers to the first question
-- Add answers to the first question
INSERT INTO ANSWER (id, text) VALUES (NEXT VALUE FOR ANSWER_SEQ, 'This is the first answer to the first question.');
INSERT INTO ANSWER (id, text) VALUES (NEXT VALUE FOR ANSWER_SEQ, 'This is the second answer to the first question.');

-- Associate the answers with the first question
INSERT INTO QUESTION_ANSWERS (QUESTION_ID, ANSWERS_ID) VALUES ((SELECT id FROM QUESTION WHERE title = 'Some Title?'), (SELECT id FROM ANSWER WHERE text = 'This is the first answer to the first question.'));
INSERT INTO QUESTION_ANSWERS (QUESTION_ID, ANSWERS_ID) VALUES ((SELECT id FROM QUESTION WHERE title = 'Some Title?'), (SELECT id FROM ANSWER WHERE text = 'This is the second answer to the first question.'));