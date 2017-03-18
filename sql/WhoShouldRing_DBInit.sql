CREATE TABLE "bcorpo" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "town" TEXT NOT NULL
);

CREATE TABLE "bfood" (
  "id" SERIAL PRIMARY KEY,
  "price" DECIMAL(12, 2) NOT NULL,
  "name" TEXT NOT NULL
);

CREATE TABLE "bfoodprovider" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "phone" TEXT NOT NULL,
  "rating" DECIMAL(12, 2)
);

CREATE TABLE "bfooditem" (
  "bfood" INTEGER NOT NULL,
  "bfoodprovider" INTEGER NOT NULL,
  PRIMARY KEY ("bfood", "bfoodprovider")
);

CREATE INDEX "idx_bfooditem__bfoodprovider" ON "bfooditem" ("bfoodprovider");

ALTER TABLE "bfooditem" ADD CONSTRAINT "fk_bfooditem__bfood" FOREIGN KEY ("bfood") REFERENCES "bfood" ("id");

ALTER TABLE "bfooditem" ADD CONSTRAINT "fk_bfooditem__bfoodprovider" FOREIGN KEY ("bfoodprovider") REFERENCES "bfoodprovider" ("id");

CREATE TABLE "bfoodproviderleaflet" (
  "id" SERIAL PRIMARY KEY,
  "image_src" TEXT NOT NULL,
  "b_food_provider" INTEGER NOT NULL
);

CREATE INDEX "idx_bfoodproviderleaflet__b_food_provider" ON "bfoodproviderleaflet" ("b_food_provider");

ALTER TABLE "bfoodproviderleaflet" ADD CONSTRAINT "fk_bfoodproviderleaflet__b_food_provider" FOREIGN KEY ("b_food_provider") REFERENCES "bfoodprovider" ("id");

CREATE TABLE "bringqueue" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "lmod" TIMESTAMP NOT NULL,
  "created_on" TIMESTAMP NOT NULL,
  "b_corpo" INTEGER NOT NULL
);

CREATE INDEX "idx_bringqueue__b_corpo" ON "bringqueue" ("b_corpo");

ALTER TABLE "bringqueue" ADD CONSTRAINT "fk_bringqueue__b_corpo" FOREIGN KEY ("b_corpo") REFERENCES "bcorpo" ("id");

CREATE TABLE "brole" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT UNIQUE NOT NULL
);

CREATE TABLE "User" (
  "id" SERIAL PRIMARY KEY,
  "login" TEXT UNIQUE NOT NULL,
  "email" TEXT UNIQUE NOT NULL,
  "password" TEXT NOT NULL,
  "lastonline" TIMESTAMP NOT NULL,
  "isonline" BOOLEAN NOT NULL,
  "b_role" INTEGER NOT NULL,
  "b_corpo" INTEGER,
  "b_ring_queue" INTEGER,
  "last_call" TIMESTAMP
);

CREATE INDEX "idx_user__b_corpo" ON "User" ("b_corpo");

CREATE INDEX "idx_user__b_ring_queue" ON "User" ("b_ring_queue");

CREATE INDEX "idx_user__b_role" ON "User" ("b_role");

ALTER TABLE "User" ADD CONSTRAINT "fk_user__b_corpo" FOREIGN KEY ("b_corpo") REFERENCES "bcorpo" ("id");

ALTER TABLE "User" ADD CONSTRAINT "fk_user__b_ring_queue" FOREIGN KEY ("b_ring_queue") REFERENCES "bringqueue" ("id");

ALTER TABLE "User" ADD CONSTRAINT "fk_user__b_role" FOREIGN KEY ("b_role") REFERENCES "brole" ("id");

CREATE TABLE "buserstatistic" (
  "id" SERIAL PRIMARY KEY,
  "name" TEXT NOT NULL,
  "stat" TEXT NOT NULL,
  "b_user" INTEGER NOT NULL,
  "b_food_provider" INTEGER
);

CREATE INDEX "idx_buserstatistic__b_food_provider" ON "buserstatistic" ("b_food_provider");

CREATE INDEX "idx_buserstatistic__b_user" ON "buserstatistic" ("b_user");

ALTER TABLE "buserstatistic" ADD CONSTRAINT "fk_buserstatistic__b_food_provider" FOREIGN KEY ("b_food_provider") REFERENCES "bfoodprovider" ("id");

ALTER TABLE "buserstatistic" ADD CONSTRAINT "fk_buserstatistic__b_user" FOREIGN KEY ("b_user") REFERENCES "User" ("id")