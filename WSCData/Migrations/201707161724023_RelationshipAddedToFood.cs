namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class RelationshipAddedToFood : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Foods", "Order_Id", "dbo.Orders");
            DropIndex("dbo.Foods", new[] { "Order_Id" });
            CreateTable(
                "dbo.OrderFoods",
                c => new
                    {
                        Order_Id = c.Int(nullable: false),
                        Food_Id = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.Order_Id, t.Food_Id })
                .ForeignKey("dbo.Orders", t => t.Order_Id, cascadeDelete: true)
                .ForeignKey("dbo.Foods", t => t.Food_Id, cascadeDelete: true)
                .Index(t => t.Order_Id)
                .Index(t => t.Food_Id);
            
            AddColumn("dbo.Users", "Food_Id", c => c.Int());
            CreateIndex("dbo.Users", "Food_Id");
            AddForeignKey("dbo.Users", "Food_Id", "dbo.Foods", "Id");
            DropColumn("dbo.Foods", "Order_Id");
        }
        
        public override void Down()
        {
            AddColumn("dbo.Foods", "Order_Id", c => c.Int());
            DropForeignKey("dbo.Users", "Food_Id", "dbo.Foods");
            DropForeignKey("dbo.OrderFoods", "Food_Id", "dbo.Foods");
            DropForeignKey("dbo.OrderFoods", "Order_Id", "dbo.Orders");
            DropIndex("dbo.OrderFoods", new[] { "Food_Id" });
            DropIndex("dbo.OrderFoods", new[] { "Order_Id" });
            DropIndex("dbo.Users", new[] { "Food_Id" });
            DropColumn("dbo.Users", "Food_Id");
            DropTable("dbo.OrderFoods");
            CreateIndex("dbo.Foods", "Order_Id");
            AddForeignKey("dbo.Foods", "Order_Id", "dbo.Orders", "Id");
        }
    }
}
