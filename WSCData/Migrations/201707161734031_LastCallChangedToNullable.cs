namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class LastCallChangedToNullable : DbMigration
    {
        public override void Up()
        {
            AlterColumn("dbo.Users", "LastCall", c => c.DateTime());
        }
        
        public override void Down()
        {
            AlterColumn("dbo.Users", "LastCall", c => c.DateTime(nullable: false));
        }
    }
}
