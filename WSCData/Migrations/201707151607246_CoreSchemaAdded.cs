namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class CoreSchemaAdded : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "dbo.Orders",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Date = c.DateTime(nullable: false),
                        FoodProvider_Id = c.Int(),
                        Queue_Id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.FoodProviders", t => t.FoodProvider_Id)
                .ForeignKey("dbo.Queues", t => t.Queue_Id)
                .Index(t => t.FoodProvider_Id)
                .Index(t => t.Queue_Id);
            
            CreateTable(
                "dbo.Foods",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        Price = c.Decimal(nullable: false, precision: 18, scale: 2),
                        Rating_Id = c.Int(),
                        Order_Id = c.Int(),
                    })
                .PrimaryKey(t => t.Id)
                .ForeignKey("dbo.FoodRatings", t => t.Rating_Id)
                .ForeignKey("dbo.Orders", t => t.Order_Id)
                .Index(t => t.Rating_Id)
                .Index(t => t.Order_Id);
            
            CreateTable(
                "dbo.FoodRatings",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.FoodProviders",
                c => new
                    {
                        Id = c.Int(nullable: false, identity: true),
                        Name = c.String(),
                        FoodProviderRating = c.Decimal(nullable: false, precision: 18, scale: 2),
                    })
                .PrimaryKey(t => t.Id);
            
            CreateTable(
                "dbo.UserOrders",
                c => new
                    {
                        User_Id = c.String(nullable: false, maxLength: 128),
                        Order_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.User_Id, t.Order_Id })
                .ForeignKey("dbo.Users", t => t.User_Id, cascadeDelete: true)
                .ForeignKey("dbo.Orders", t => t.Order_Id, cascadeDelete: true)
                .Index(t => t.User_Id)
                .Index(t => t.Order_Id);
            
            AddColumn("dbo.Users", "LastCall", c => c.DateTime(nullable: false));
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.UserOrders", "Order_Id", "dbo.Orders");
            DropForeignKey("dbo.UserOrders", "User_Id", "dbo.Users");
            DropForeignKey("dbo.Orders", "Queue_Id", "dbo.Queues");
            DropForeignKey("dbo.Orders", "FoodProvider_Id", "dbo.FoodProviders");
            DropForeignKey("dbo.Foods", "Order_Id", "dbo.Orders");
            DropForeignKey("dbo.Foods", "Rating_Id", "dbo.FoodRatings");
            DropIndex("dbo.UserOrders", new[] { "Order_Id" });
            DropIndex("dbo.UserOrders", new[] { "User_Id" });
            DropIndex("dbo.Foods", new[] { "Order_Id" });
            DropIndex("dbo.Foods", new[] { "Rating_Id" });
            DropIndex("dbo.Orders", new[] { "Queue_Id" });
            DropIndex("dbo.Orders", new[] { "FoodProvider_Id" });
            DropColumn("dbo.Users", "LastCall");
            DropTable("dbo.UserOrders");
            DropTable("dbo.FoodProviders");
            DropTable("dbo.FoodRatings");
            DropTable("dbo.Foods");
            DropTable("dbo.Orders");
        }
    }
}
