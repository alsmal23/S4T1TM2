--add mock users, valid names include but are not limited to
--bobby, hank, Boomhauer etc.
INSERT INTO `User`
VALUES ('48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
        'Bobby',
        'hashword',
        'Hill');
--add mock projects (must be propane and propane and/or propane accessories related
INSERT INTO `Project`
VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
        '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
        "Test Project");
-- add mock permissions
INSERT INTO `Permission`
VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
        '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073',
        'permission_OWNER');

--add mock size sets, or tags
INSERT INTO `SizeAttribute`
VALUES ('de9590a0-49bd-4d6a-9aab-265f12414b29',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        "Small",
        1);

INSERT INTO `SizeAttribute`
VALUES ('79079e58-384b-4fab-bfa6-a4a097c780c9',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        "Medium",
        5);

INSERT INTO `SizeAttribute`
VALUES ('04fdb5f2-c87e-48da-8f27-72dd78f4d96f',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        "Large",
        10);

--add mock pursuits,
INSERT INTO `Pursuit`
VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        'Test Task',
        'TASK');
INSERT INTO `Pursuit`
VALUES ('d97352d2-973a-4e35-bd1e-300e7abf63c7',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        'Test Task 1',
        'TASK');
INSERT INTO `Pursuit`
VALUES ('052099e1-6dfe-45f8-bd6a-eb317789e288',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        'Test Task 3',
        'TASK');

--add mock tasks, and propane references here
INSERT INTO `Task` (pursuit_uuid, size_uuid, assignee_uuid)
VALUES ('78ce5303-2826-446e-bb1c-f8c092e87930',
        'de9590a0-49bd-4d6a-9aab-265f12414b29',
        '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073');

INSERT INTO `Task` (pursuit_uuid, size_uuid, assignee_uuid)
VALUES ('d97352d2-973a-4e35-bd1e-300e7abf63c7',
        'de9590a0-49bd-4d6a-9aab-265f12414b29',
        '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073');

INSERT INTO `Task` (pursuit_uuid, size_uuid, assignee_uuid)
VALUES ('052099e1-6dfe-45f8-bd6a-eb317789e288',
        '04fdb5f2-c87e-48da-8f27-72dd78f4d96f',
        '48edc7cf-bdf8-4b3d-b7c5-2d2ba151c073');

--add a sprint
INSERT INTO `pursuit`
VALUES ('2f2cd0af-2841-4107-99e2-965c6bd41cdd',
        '78ce5303-2826-446e-bb1c-f8c092e87930',
        'Sprint 1',
        'SPRINT');

INSERT INTO `Sprint` (pursuit_uuid, due_date)
VALUES ('2f2cd0af-2841-4107-99e2-965c6bd41cdd',
        julianday('now', '+2 days'));