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
  "password" TEXT NOT NULL,
  "last_call" DATE,
  "b_queue" INTEGER NOT NULL
);

CREATE INDEX "idx_buser__b_queue" ON "buser" ("b_queue");

CREATE INDEX "idx_buser__b_role" ON "buser" ("b_role");

ALTER TABLE "buser" ADD CONSTRAINT "fk_buser__b_queue" FOREIGN KEY ("b_queue") REFERENCES "bqueue" ("id");

ALTER TABLE "buser" ADD CONSTRAINT "fk_buser__b_role" FOREIGN KEY ("b_role") REFERENCES "brole" ("id")