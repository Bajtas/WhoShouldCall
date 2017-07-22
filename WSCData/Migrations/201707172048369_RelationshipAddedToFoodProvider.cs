namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class RelationshipAddedToFoodProvider : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Foods", "Rating_Id", "dbo.FoodRatings");
            DropIndex("dbo.Foods", new[] { "Rating_Id" });
            AddColumn("dbo.Foods", "FoodProvider_Id", c => c.Int());
            CreateIndex("dbo.Foods", "FoodProvider_Id");
            AddForeignKey("dbo.Foods", "FoodProvider_Id", "dbo.FoodProviders", "Id");
            DropColumn("dbo.Foods", "Rating_Id");
            DropTable("dbo.FoodRatings");
        }
        
        public override void Down()
        {
            CreateTable(
                "dbo.FoodRatings",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                    })
                .PrimaryKey(t => t.Id);
            
            AddColumn("dbo.Foods", "Rating_Id", c => c.Int());
            DropForeignKey("dbo.Foods", "FoodProvider_Id", "dbo.FoodProviders");
            DropIndex("dbo.Foods", new[] { "FoodProvider_Id" });
            DropColumn("dbo.Foods", "FoodProvider_Id");
            CreateIndex("dbo.Foods", "Rating_Id");
            AddForeignKey("dbo.Foods", "Rating_Id", "dbo.FoodRatings", "Id");
        }
    }
}
