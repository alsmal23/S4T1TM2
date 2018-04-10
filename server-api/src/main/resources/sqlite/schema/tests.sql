INSERT INTO `User` VALUES ('48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
                           'bobby',
                           'hashword',
                           'bobbys display name'
);

INSERT INTO `Project` VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
                              '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
                              "Test Project");

INSERT INTO `Permission` VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
                                 '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
                                 'permission_OWNER');




INSERT INTO `Pursuit` VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
                              '78ce5303-2826-446e-bb1c-f8c092e87930',
                              'Test Task',
                              'TASK');
INSERT INTO `Pursuit` VALUES ('d97352d2-973a-4e35-bd1e-300e7abf63c7',
                              '78ce5303-2826-446e-bb1c-f8c092e87930',
                              'Test Task 1',
                              'TASK');
INSERT INTO `Pursuit` VALUES ('052099e1-6dfe-45f8-bd6a-eb317789e288',
                              '78ce5303-2826-446e-bb1c-f8c092e87930',
                              'Test Task 3',
                              'TASK');
--edit here if
--INSERT INTO  `Task` (pursuit_uuid,size_uuid,assignee_uuid,description)
 -- VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',,,);