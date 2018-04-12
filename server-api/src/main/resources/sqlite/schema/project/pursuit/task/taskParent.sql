CREATE VIEW task_parent AS
  SELECT
    Task.pursuit_uuid AS task_uuid,
    pursuit.uuid      AS parent_uuid,
    pursuit.type      AS parent_type
  FROM Task
    JOIN Pursuit ON Task.parent_uuid = Pursuit.uuid;