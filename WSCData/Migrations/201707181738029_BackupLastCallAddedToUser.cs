namespace WSCData.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class BackupLastCallAddedToUser : DbMigration
    {
        public override void Up()
        {
            AddColumn("dbo.Users", "LastCallBackup", c => c.DateTime());
        }
        
        public override void Down()
        {
            DropColumn("dbo.Users", "LastCallBackup");
        }
    }
}
