namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class IdentityChangesToUserName : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("dbo.Users", "Queue_Id", "dbo.Queues");
            AddColumn("dbo.Users", "Queue_Id1", c => c.Int());
            AddColumn("dbo.Users", "Queue_Id2", c => c.Int());
            CreateIndex("dbo.Users", "Queue_Id1");
            CreateIndex("dbo.Users", "Queue_Id2");
            AddForeignKey("dbo.Users", "Queue_Id1", "dbo.Queues", "Id");
            AddForeignKey("dbo.Users", "Queue_Id2", "dbo.Queues", "Id");
        }
        
        public override void Down()
        {
            DropForeignKey("dbo.Users", "Queue_Id2", "dbo.Queues");
            DropForeignKey("dbo.Users", "Queue_Id1", "dbo.Queues");
            DropIndex("dbo.Users", new[] { "Queue_Id2" });
            DropIndex("dbo.Users", new[] { "Queue_Id1" });
            DropColumn("dbo.Users", "Queue_Id2");
            DropColumn("dbo.Users", "Queue_Id1");
            AddForeignKey("dbo.Users", "Queue_Id", "dbo.Queues", "Id");
        }
    }
}
