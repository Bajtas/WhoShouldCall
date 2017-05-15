CREATE TABLE "bqueue" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "company_name" TEXT NOT NULL
);

CREATE TABLE "brole" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL
);

CREATE TABLE "buser" (
  "id" SERIAL PRIMARY KEY,
  "b_role" INTEGER NOT NULL,
  "login" TEXT NOT NULL,
  "password" TEXT NOT NULL
);

CREATE INDEX "idx_buser__b_role" ON "buser" ("b_role");

ALTER TABLE "buser" ADD CONSTRAINT "fk_buser__b_role" FOREIGN KEY ("b_role") REFERENCES "brole" ("id");

CREATE TABLE "bqueueuser" (
  "id" SERIAL PRIMARY KEY,
  "b_queue" INTEGER NOT NULL,
  "b_user" INTEGER NOT NULL,
  "last_call" TIMESTAMP NOT NULL
);

CREATE INDEX "idx_bqueueuser__b_queue" ON "bqueueuser" ("b_queue");

CREATE INDEX "idx_bqueueuser__b_user" ON "bqueueuser" ("b_user");

ALTER TABLE "bqueueuser" ADD CONSTRAINT "fk_bqueueuser__b_queue" FOREIGN KEY ("b_queue") REFERENCES "bqueue" ("id");

ALTER TABLE "bqueueuser" ADD CONSTRAINT "fk_bqueueuser__b_user" FOREIGN KEY ("b_user") REFERENCES "buser" ("id")